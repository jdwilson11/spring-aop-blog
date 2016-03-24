package org.jdw.blog.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.jdw.blog.common.executable.InterfaceWithAnnotation;
import org.jdw.blog.common.executable.InterfaceWithoutAnnotation;
import org.jdw.blog.common.executable.NoInterface;

public class CommonTest {

    public static void testHystrixWrappedMethod_(InterfaceWithAnnotation executableMethods,
            boolean hystrixExecutionExpected) {

        long potentialHystrixThreadId = executableMethods.hystrixWrappedGetCurrentThreadId();

        validateTest(potentialHystrixThreadId, hystrixExecutionExpected);
    }

    public static void testNestedHystrixWrappedMethod_(InterfaceWithAnnotation executableMethods,
            boolean hystrixExecutionExpected) {

        long potentialHystrixThreadId = executableMethods.nestedHystrixWrappedGetCurrentThreadId();

        validateTest(potentialHystrixThreadId, hystrixExecutionExpected);
    }

    public static void testHystrixWrappedMethod_(InterfaceWithoutAnnotation executableMethods,
            boolean hystrixExecutionExpected) {

        long potentialHystrixThreadId = executableMethods.hystrixWrappedGetCurrentThreadId();

        validateTest(potentialHystrixThreadId, hystrixExecutionExpected);
    }

    public static void testNestedHystrixWrappedMethod_(InterfaceWithoutAnnotation executableMethods,
            boolean hystrixExecutionExpected) {

        long potentialHystrixThreadId = executableMethods.nestedHystrixWrappedGetCurrentThreadId();

        validateTest(potentialHystrixThreadId, hystrixExecutionExpected);
    }

    public static void testHystrixWrappedMethod_Impl(NoInterface executableMethods,
            boolean hystrixExecutionExpected) {

        long potentialHystrixThreadId = executableMethods.hystrixWrappedGetCurrentThreadId();

        validateTest(potentialHystrixThreadId, hystrixExecutionExpected);
    }

    public static void testNestedHystrixWrappedMethod_Impl(NoInterface executableMethods,
            boolean hystrixExecutionExpected) {

        long potentialHystrixThreadId = executableMethods.nestedHystrixWrappedGetCurrentThreadId();

        validateTest(potentialHystrixThreadId, hystrixExecutionExpected);
    }

    private static void validateTest(long potentialHystrixThreadId,
            boolean hystrixExecutionExpected) {

        long thisThreadId = Thread.currentThread().getId();

        if (hystrixExecutionExpected) {
            // If the HystrixAspect triggered on the call to executableMethods,
            // the potentialHystrixThreadId came from a different thread.
            assertNotEquals(thisThreadId, potentialHystrixThreadId);

        } else {
            // Otherwise it came from this same thread.
            assertEquals(thisThreadId, potentialHystrixThreadId);
        }
    }

}
