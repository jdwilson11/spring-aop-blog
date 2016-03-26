package org.jdw.blog.common.executable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("nonAnnotatedImplForInterfaceWithAnnotation")
public class NonAnnotatedImplForInterfaceWithAnnotation
        implements InterfaceWithAnnotation {

    final static Logger log = LoggerFactory.getLogger(NonAnnotatedImplForInterfaceWithAnnotation.class);

    public long getCurrentThreadId() {
        long currentThreadId = Thread.currentThread().getId();
        log.info("---");
        log.info("Now inside " + getClass().getSimpleName() + ".getCurrentThreadId");
        log.info("Current thread ID: " + currentThreadId);
        Thread.dumpStack();
        return currentThreadId;
    }

    // Only has @HystrixWrapper at the interface level,
    // unlike all other concrete classes in this package
    @Override
    public long hystrixWrappedGetCurrentThreadId() {
        return getCurrentThreadId();
    }

    @Override
    public long nestedHystrixWrappedGetCurrentThreadId() {
        return hystrixWrappedGetCurrentThreadId();
    }

}
