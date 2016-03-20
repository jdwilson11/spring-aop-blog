package org.jdw.blog.common.executable;

import org.jdw.blog.common.annotation.HystrixWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class NoInterface {

    final static Logger log = LoggerFactory.getLogger(NoInterface.class);

    public long getCurrentThreadId() {
        long currentThreadId = Thread.currentThread().getId();
        log.info("---");
        log.info("Now inside " + getClass().getSimpleName() + ".getCurrentThreadId");
        log.info("Current thread ID: " + currentThreadId);
        Thread.dumpStack();
        return currentThreadId;
    }

    @HystrixWrapper(commandGroupKey = "blog")
    public long hystrixWrappedGetCurrentThreadId() {
        return getCurrentThreadId();
    }

    public long nestedHystrixWrappedGetCurrentThreadId() {
        return hystrixWrappedGetCurrentThreadId();
    }

}
