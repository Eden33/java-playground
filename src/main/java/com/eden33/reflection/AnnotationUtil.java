package com.eden33.reflection;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

/**
 * Discover annotations on methods of a class using Java Reflection API.
 * <p>
 * Code was written when attended the Udemy course "Advanced Java Topics: Java Reflection - Master Class" by Michael Pogrebinsky.
 * <p>
 * Course chapter: "Section 7: Annotations with Java Reflection"
 */
public class AnnotationUtil {
    

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface MethodMarker {
        int order() default 0;
    }
    
    private static List<Method> getAllAnnotatedMethods(Object input) {
        List<Method> annotatedMethods = new ArrayList<>();

        Method[] methods = input.getClass().getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(MethodMarker.class)) {
                annotatedMethods.add(method);
            }
        }
        
        return annotatedMethods; 
    }
    
    /**
     * Retrieves all methods annotated with {@code MethodMarker} annotation from the given object
     * and returns them in a sorted order based on the `order` value specified in the annotation.
     *
     * @param input The object whose annotated methods are to be retrieved.
     * @return A list of methods annotated with {@code MethodMarker}, sorted by the `order` value
     *         defined in the annotation.
     * @throws NullPointerException If the input object is null.
     */
    public static List<Method> getAllAnnotatedMethodsOrdered(Object input) {
        if (input == null) {
            throw new NullPointerException("Input object cannot be null.");
        }

        List<Method> annotatedMethods = getAllAnnotatedMethods(input);
        
        annotatedMethods.sort((method1, method2) -> {
            MethodMarker annotation1 = method1.getAnnotation(MethodMarker.class);
            MethodMarker annotation2 = method2.getAnnotation(MethodMarker.class);
            return Integer.compare(annotation1.order(), annotation2.order());
        });
        
        return annotatedMethods;
    }
}