package com.eden33.reflection;

import java.util.*;
import java.lang.reflect.*;

/**
 * Represents a simple testing framework that can run test methods in a test class.
 * <p>
 * Code was written when attended the Udemy course "Advanced Java Topics: Java Reflection - Master Class" by Michael Pogrebinsky.
 * <p>
 * Course chapter: "Section 5: Methods Discovery & Invocation"
 */
public class TestingFramework {

    /**
     * Run all the test methods in the given test class.
     * <ol>
     * <li>If the test class has a static method with the name "beforeClass" that returns void and accepts no parameters, invoke it using
     * reflection</li>
     * <li>For each method, if the method name starts with "test" that returns void and accepts no
     * parameters, invoke the method using reflection. Before execution the test method execute the
     * non static method with the name "setupTest" if it exists.</li>
     * <li>If the test class has a static method with the name "afterClass" that returns void and accepts no parameters, invoke it using
     * reflection</li>
     * </ol>
     * 
     * @param testClass the test class to run the test methods from
     * @throws Throwable if any exception is thrown during the invocation of the methods
     */
    public void runTestSuite(Class<?> testClass) throws Throwable {

        // instantiate the test class
        Object testInstance = testClass.newInstance();

        Method[] methods = testClass.getDeclaredMethods();

        // Find and invoke the method with the name "beforeClass"
        Method beforeClassMethod = findMethodByName(methods, "beforeClass", true);
        if (beforeClassMethod != null) {
            beforeClassMethod.invoke(null);
        }

        Method setupTest = findMethodByName(methods, "setupTest", false);
        List<Method> tests = findMethodsByPrefix(methods, "test");

        // Find and invoke all the methods that start with the name "test"
        for (Method method : tests) {
            if (setupTest != null) {
                setupTest.invoke(testInstance);
            }
            method.invoke(testInstance);
        }

        // find and invoke the method with the name "afterClass"
        Method afterClassMethod = findMethodByName(methods, "afterClass", true);
        if (afterClassMethod != null) {
            afterClassMethod.invoke(null);
        }
    }

    /**
     * Helper method to find a method by name.
     * <p>
     * The method must be static if the parameter shouldBeStatic is true.
     * <p>
     * The method must return void and accept no parameters.
     * 
     * @return null if the method with the given name does not, otherwise the method.
     */
    private Method findMethodByName(Method[] methods, String name, boolean shouldBeStatic) {
        for (Method method : methods) {
            if (method.getName().equals(name)
                    && Modifier.isStatic(method.getModifiers()) == shouldBeStatic
                    && method.getReturnType().equals(Void.TYPE)
                    && method.getParameterCount() == 0) {
                return method;
            }
        }
        return null;
    }

    /**
     * Helper method to find all the methods that start with the given prefix.
     * <p>
     * The method must return void and accept no parameters.
     * @return A list of methods that start with the given prefix or an empty list if no such methods.
     */
    private List<Method> findMethodsByPrefix(Method[] methods, String prefix) {
        List<Method> result = new ArrayList<>();
        for (Method method : methods) {
            if (method.getName().startsWith(prefix) && method.getReturnType().equals(Void.TYPE)
                    && method.getParameterCount() == 0) {
                result.add(method);
            }
        }
        return result;
    }
}
