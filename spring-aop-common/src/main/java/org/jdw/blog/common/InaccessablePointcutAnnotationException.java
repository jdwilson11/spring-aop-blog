package org.jdw.blog.common;

import org.jdw.blog.common.aspect.HystrixAspect;

/**
 * Thrown by {@link HystrixAspect} when an aspect was invoked<br>
 * due to the presence of an annotation that could not be found using the joinPoint.
 * <p>
 * Can occur when using Spring's JDK style proxies<br>
 * (which implement interfaces and delegate to concrete classes)<br>
 * instead of CCGLIB-style 'subclass' proxies (which extend concrete classes),<br>
 * when using an interface that lacks an annotation whose concrete class has the annotation.
 * <p>
 * The JDK style proxy can't find the annotation because it wasn't on the interface,<br>
 * even though the aspect got triggered due to the concrete class having it.<br>
 * CGLIB-style subclass proxies retain visibility the annotation.
 */
public class InaccessablePointcutAnnotationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

}
