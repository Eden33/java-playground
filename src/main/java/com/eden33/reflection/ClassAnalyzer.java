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

        Set<Class<?>> allImplementedInterfaces = findAllImplementedInterfaces(inputClass);
        
        if(allImplementedInterfaces.size() == 0) {
            System.out.println(inputClass.getSimpleName() + " class. No implemented interfaces found.");
        } else {
            System.out.println(inputClass.getSimpleName() + " class. All implemented interfaces: ");
            for (Class<?> implementedInterface : allImplementedInterfaces) {
                System.out.println(implementedInterface.getName());
            }           
        }
        System.out.println(""); 
    }
    
    private static boolean isJdkClass(Class<?> inputClass) {  
        String packageName = inputClass.getPackageName();

        return JDK_PACKAGE_PREFIXES.stream()
                .anyMatch(packagePrefix -> packageName.startsWith(packagePrefix));
    }

    /**
     * Returns all the interfaces that the current input class implements.
     * Note: If the input is an interface itself, the method returns all the interfaces the 
     * input interface extends.
     */
    private static Set<Class<?>> findAllImplementedInterfaces(Class<?> input) {
        Set<Class<?>> allImplementedInterfaces = new HashSet<>();
        
        Class<?>[] inputInterfaces = input.getInterfaces();
        for (Class<?> currentInterface : inputInterfaces) {
            allImplementedInterfaces.add(currentInterface);
            Set<Class<?>> superInterfaces = findAllImplementedInterfaces(currentInterface);
            if(superInterfaces.size() > 0) {
                allImplementedInterfaces.addAll(superInterfaces);
            }
        }

        return allImplementedInterfaces;
    }
}