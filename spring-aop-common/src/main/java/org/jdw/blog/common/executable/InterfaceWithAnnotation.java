package org.jdw.blog.common.executable;

import org.jdw.blog.common.annotation.HystrixWrapper;

public interface InterfaceWithAnnotation {

    @HystrixWrapper(commandGroupKey = "blog")
    public long hystrixWrappedGetCurrentThreadId();

    public long nestedHystrixWrappedGetCurrentThreadId();

}