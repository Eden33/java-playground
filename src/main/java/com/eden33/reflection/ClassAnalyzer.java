package com.eden33.reflection;

import java.util.*;

/**
 * Analyzes a class using Java Reflection API.
 * <p>
 * Code was written when attended the Udemy course "Advanced Java Topics: Java Reflection - Master Class" by Michael Pogrebinsky.
 * <p>
 * Course chapter: "Section 1: Introduction to Reflection"
 */
public class ClassAnalyzer {
    private static final List<String> JDK_PACKAGE_PREFIXES =
                Arrays.asList("com.sun.", "java", "javax", "jdk", "org.w3c", "org.xml");
                
    public static void analyze(Class<?> inputClass) {
        
        System.out.println("Class: " + inputClass.getName());

        System.out.println("Is Primitive: " + inputClass.isPrimitive());
        System.out.println("Is Interface: " + inputClass.isInterface());
        System.out.println("Is Enum: " + inputClass.isEnum());
        System.out.println("Name: " + inputClass.getSimpleName());
        System.out.println("Is part of JDK: " + isJdkClass(inputClass));
        System.out.println("");
        
    }
    
    /*********** Helper Methods ***************/
    
    public static boolean isJdkClass(Class<?> inputClass) {  
        String packageName = inputClass.getPackageName();

        return JDK_PACKAGE_PREFIXES.stream()
                .anyMatch(packagePrefix -> packageName.startsWith(packagePrefix));
    }

    public static void main(String[] args) {
        analyze(Boolean.class);
        analyze(boolean.class);
        Address6 address = new Address6("Main St", (short) 101, "12345");
        analyze(address.getClass());
    }
}