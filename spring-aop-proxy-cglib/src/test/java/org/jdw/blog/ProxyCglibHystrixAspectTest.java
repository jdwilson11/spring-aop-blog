package org.jdw.blog;

import org.jdw.blog.common.CommonTest;
import org.jdw.blog.common.executable.ImplForInterfaceWithAnnotation;
import org.jdw.blog.common.executable.ImplForInterfaceWithoutAnnotation;
import org.jdw.blog.common.executable.InterfaceWithAnnotation;
import org.jdw.blog.common.executable.InterfaceWithoutAnnotation;
import org.jdw.blog.common.executable.NoInterface;
import org.jdw.blog.common.executable.NonAnnotatedImplForInterfaceWithAnnotation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class ProxyCglibHystrixAspectTest extends BaseSpringJUnitTest {

    @Autowired
    @Qualifier("implForInterfaceWithAnnotation")
    private InterfaceWithAnnotation interfaceWithAnnotation;

    @Autowired
    private ImplForInterfaceWithAnnotation implForInterfaceWithAnnotation;

    @Autowired
    private InterfaceWithoutAnnotation interfaceWithoutAnnotation;

    @Autowired
    private ImplForInterfaceWithoutAnnotation implForInterfaceWithoutAnnotation;

    @Autowired
    private NoInterface noInterface;

    @Autowired
    private NonAnnotatedImplForInterfaceWithAnnotation nonAnnotatedImplForInterfaceWithAnnotation;

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

    @Test
    public void testHystrixWrappedMethod_ImplForInterfaceWithAnnotation() {
        // The HystrixAspect will trigger, wrapping the target method in a new thread.
        CommonTest.testHystrixWrappedMethod_(implForInterfaceWithAnnotation, true);
    }

    @Test
    public void testNestedHystrixWrappedMethod_ImplForInterfaceWithAnnotation() {
        // The HystrixAspect won't trigger.
        CommonTest.testNestedHystrixWrappedMethod_(implForInterfaceWithAnnotation, false);
    }

    @Test
    public void testHystrixWrappedMethod_InterfaceWithoutAnnotation() {
        // The HystrixAspect will trigger, wrapping the target method in a new thread.
        CommonTest.testHystrixWrappedMethod_(interfaceWithoutAnnotation, true);
    }

    @Test
    public void testNestedHystrixWrappedMethod_InterfaceWithoutAnnotation() {
        // The HystrixAspect won't trigger.
        CommonTest.testNestedHystrixWrappedMethod_(interfaceWithoutAnnotation, false);
    }

    @Test
    public void testHystrixWrappedMethod_ImplForInterfaceWithoutAnnotation() {
        // The HystrixAspect will trigger, wrapping the target method in a new thread.
        CommonTest.testHystrixWrappedMethod_(implForInterfaceWithoutAnnotation, true);
    }

    @Test
    public void testNestedHystrixWrappedMethod_ImplForInterfaceWithoutAnnotation() {
        // The HystrixAspect won't trigger.
        CommonTest.testNestedHystrixWrappedMethod_(implForInterfaceWithoutAnnotation, false);
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

    @Test
    public void testHystrixWrappedMethod_NonAnnotatedImplForInterfaceWithAnnotation() {
        // The HystrixAspect won't trigger.
        CommonTest.testHystrixWrappedMethod_(nonAnnotatedImplForInterfaceWithAnnotation, false);
    }

    @Test
    public void testNestedHystrixWrappedMethod_NonAnnotatedImplForInterfaceWithAnnotation() {
        // The HystrixAspect won't trigger.
        CommonTest.testNestedHystrixWrappedMethod_(nonAnnotatedImplForInterfaceWithAnnotation, false);
    }

}
