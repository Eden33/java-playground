package com.eden33.reflection;

import java.lang.reflect.Field;

/**
 * A simple size calculator to estimate the JVM memory size of an arbitrary object.
 * <p>
 * Code was written when attended the Udemy course "Advanced Java Topics: Java Reflection - Master Class" by Michael Pogrebinsky.
 * <p>
 * Course chapter: "Section 3: Inspection of Fields & Arrays"
 */
public class ObjectSizeCalculator {
    private static final long HEADER_SIZE = 12;
    private static final long REFERENCE_SIZE = 4;
    
    /**
     * Estimates the size of an object in bytes using reflection.
     * 
     * The calculation includes:
     * - A fixed header size (12 bytes).
     * - A fixed reference size (4 bytes).
     * - The size of each field in the object, including primitive types, Strings, 
     *   and references to other objects.
     * 
     * @param input The object whose size is to be estimated.
     * @return The estimated size of the object in bytes.
     * @throws RuntimeException If a field cannot be accessed.
     */
    public long sizeOfObject(Object input) {
        if (input == null) {
            return 0;
        }

        long size = HEADER_SIZE + REFERENCE_SIZE; // Start with header and reference size
        Class<?> clazz = input.getClass();
        
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true); // Allow access to private fields
            Class<?> fieldType = field.getType();
            
            if (fieldType.isPrimitive()) {
                size += sizeOfPrimitiveType(fieldType);
            } else if (fieldType.equals(String.class)) {
                try {
                    String value = (String) field.get(input);
                    size += value != null ? sizeOfString(value) : 0;
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Unable to access field: " + field.getName(), e);
                }
            } else {
                size += REFERENCE_SIZE; // For non-primitive, non-String fields, add reference size
            }
        }
        
        return size;
    }
    
    /*************** Helper methods ********************************/    
    private long sizeOfPrimitiveType(Class<?> primitiveType) {
        if (primitiveType.equals(int.class)) {
            return 4;
        } else if (primitiveType.equals(long.class)) {
            return 8;
        } else if (primitiveType.equals(float.class)) {
            return 4;
        } else if (primitiveType.equals(double.class)) {
            return 8;
        } else if (primitiveType.equals(byte.class)) {
            return 1;
        } else if (primitiveType.equals(short.class)) {
            return 2;
        }
        throw new IllegalArgumentException(String.format("Type: %s is not supported", primitiveType));
    }
    
    private long sizeOfString(String inputString) {
        int stringBytesSize = inputString.getBytes().length;
        return HEADER_SIZE + REFERENCE_SIZE + stringBytesSize;
    }
}