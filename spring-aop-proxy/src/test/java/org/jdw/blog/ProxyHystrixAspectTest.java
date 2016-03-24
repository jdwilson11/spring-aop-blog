package org.jdw.blog;

import org.jdw.blog.common.CommonTest;
import org.jdw.blog.common.InaccessablePointcutAnnotationException;
import org.jdw.blog.common.executable.InterfaceWithAnnotation;
import org.jdw.blog.common.executable.InterfaceWithoutAnnotation;
import org.jdw.blog.common.executable.NoInterface;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProxyHystrixAspectTest extends BaseSpringJUnitTest {

    @Autowired
    private InterfaceWithAnnotation interfaceWithAnnotation;

    // Can't autowire an implementation without cglib
    // @Autowired
    // private ImplForInterfaceWithAnnotation implForInterfaceWithAnnotation;

    @Autowired
    private InterfaceWithoutAnnotation interfaceWithoutAnnotation;

    // Can't autowire an implementation without cglib
    // @Autowired
    // private ImplForInterfaceWithoutAnnotation implForInterfaceWithoutAnnotation;

    @Autowired
    private NoInterface noInterface;

    @Test
    public void testHystrixWrappedMethod_InterfaceWithAnnotation() {
        // The HystrixAspect will trigger, wrapping the target method in a new thread.
        CommonTest.testHystrixWrappedMethod_(interfaceWithAnnotation, true);
    }

    @Test
    public void testNestedHystrixWrappedMethod_InterfaceWithAnnotation() {
        // The HystrixAspect won't trigger.
        CommonTest.testNestedHystrixWrappedMethod_(interfaceWithAnnotation, false);
    }

    @Test(expected = InaccessablePointcutAnnotationException.class)
    public void testHystrixWrappedMethod_InterfaceWithoutAnnotation() {
        CommonTest.testHystrixWrappedMethod_(interfaceWithoutAnnotation, false);
    }

    @Test
    public void testNestedHystrixWrappedMethod_InterfaceWithoutAnnotation() {
        // The HystrixAspect won't trigger.
        CommonTest.testNestedHystrixWrappedMethod_(interfaceWithoutAnnotation, false);
    }

    @Test
    public void testHystrixWrappedMethod_NoInterface() {
        // The HystrixAspect will trigger, wrapping the target method in a new thread.
        CommonTest.testHystrixWrappedMethod_Impl(noInterface, true);
    }

    @Test
    public void testNestedHystrixWrappedMethod_NoInterface() {
        // The HystrixAspect won't trigger.
        CommonTest.testNestedHystrixWrappedMethod_Impl(noInterface, false);
    }

}
