package org.jdw.blog.common.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.jdw.blog.common.InaccessablePointcutAnnotationException;
import org.jdw.blog.common.SampleAutowiredComponent;
import org.jdw.blog.common.annotation.HystrixWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

@Aspect
@Component
public class HystrixAspect {

    @Autowired
    private SampleAutowiredComponent sampleAutowiredComponent;

    final static Logger log = LoggerFactory.getLogger(HystrixAspect.class);

    // Alternative syntax for reusing the pointcut with multiple types of aspect
    //
    // @Pointcut("within(org.jdw.blog..*) && @annotation(org.jdw.blog.common.annotation.HystrixWrapper)")
    // public void hystrixAspectPointcut() {
    // }
    //
    // @Around("hystrixAspectPointcut()")

    // Only looking within this app's packages to improve performance
    @Around("within(org.jdw.blog..*) && @annotation(org.jdw.blog.common.annotation.HystrixWrapper)")
    public Object around(final ProceedingJoinPoint joinPoint) {

        Assert.notNull(sampleAutowiredComponent);

        log.info("---");
        log.info("Performing 'Around' operation inside HystrixAspect");
        log.info("Current thread ID: " + Thread.currentThread().getId());

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        HystrixWrapper annotation = method.getAnnotation(HystrixWrapper.class);
        if (annotation == null) {
            // Can occur when not using CCGLIB-style 'subclass' proxies,
            // when using an interface that has a concrete class
            // that has the annotation.
            throw new InaccessablePointcutAnnotationException();
        }

        String commandGroupKey = annotation.commandGroupKey();

        // Continue execution in a new Hystrix thread
        HystrixCommand<Object> command = new HystrixCommand<Object>(
                HystrixCommandGroupKey.Factory.asKey(commandGroupKey)) {

            @Override
            protected Object run() throws Exception {
                try {
                    return joinPoint.proceed();
                } catch (Exception e) {
                    throw e;
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        };
        return command.execute();
    }

}
