package org.jdw.blog.common.executable;

import org.jdw.blog.common.annotation.HystrixWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ImplForInterfaceWithoutAnnotation
        implements InterfaceWithoutAnnotation {

    final static Logger log = LoggerFactory.getLogger(ImplForInterfaceWithoutAnnotation.class);

    public long getCurrentThreadId() {
        long currentThreadId = Thread.currentThread().getId();
        log.info("---");
        log.info("Now inside " + getClass().getSimpleName() + ".getCurrentThreadId");
        log.info("Current thread ID: " + currentThreadId);
        Thread.dumpStack();
        return currentThreadId;
    }

    @Override
    @HystrixWrapper(commandGroupKey = "blog")
    public long hystrixWrappedGetCurrentThreadId() {
        return getCurrentThreadId();
    }

    @Override
    public long nestedHystrixWrappedGetCurrentThreadId() {
        return hystrixWrappedGetCurrentThreadId();
    }

}
