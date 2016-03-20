package org.jdw.blog.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HystrixWrapper {

    /**
     * Used in properties to determine the size of the threadpool allocated<br>
     * to Hystrix commands running in this group.
     * <p>
     * For example, given the commandGroupKey <b>blog</b> the property:<br>
     * <i>hystrix.threadpool.<b>blog</b>.coreSize</i><br>
     * could override the default settings for that group.
     * <p>
     * The default settings can be changed from 10 using:<br>
     * <i>hystrix.threadpool.default.coreSize</i>
     * <p>
     * The commandGroupKey can also be used in other properties, as per<br>
     * <a href="https://github.com/Netflix/Hystrix/wiki/Configuration">Netflix's documentation</a>.
     */
    public String commandGroupKey();

}
