package com.eden33.reflection;
import java.lang.reflect.*;

/**
 * Represents a json writer class that can be used to serialize objects to json.
 * <p>
 * Code was written when attended the Udemy course "Advanced Java Topics: Java Reflection - Master Class" by Michael Pogrebinsky.
 * <p>
 * Course chapter: "Section 6: Java Modifieres Discovery and Analysis"
 */
public class JsonWriter6 {
    
    /**
     * Serializes the provided instance into a JSON String representation.
     * <p>
     * Static fields are ignored as they belong to the class and not to the instance.
     * <p>
     * Transient fields are ignored they should not be serialized.
     * 
     * @return a JSON String representation of the provided instance.
     */
    public static String objectToJson(Object instance) throws IllegalAccessException {
        Field[] fields = instance.getClass().getDeclaredFields();
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            if (field.isSynthetic()) {
                continue;
            }

            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            if(Modifier.isTransient(field.getModifiers())) {
                continue;
            }

            stringBuilder.append(formatStringValue(field.getName()));

            stringBuilder.append(":");

            if (field.getType().isPrimitive()) {
                stringBuilder.append(formatPrimitiveValue(field, instance));
            } else if (field.getType().equals(String.class)) {
                stringBuilder.append(formatStringValue(field.get(instance).toString()));
            }

            if (i != fields.length - 1) {
                stringBuilder.append(",");
            }
        }

        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private static String formatPrimitiveValue(Field field, Object parentInstance) throws IllegalAccessException {
        if (field.getType().equals(boolean.class)
                || field.getType().equals(int.class)
                || field.getType().equals(long.class)
                || field.getType().equals(short.class)) {
            return field.get(parentInstance).toString();
        } else if (field.getType().equals(double.class) || field.getType().equals(float.class)) {
            return String.format("%.02f", field.get(parentInstance));
        }

        throw new RuntimeException(String.format("Type : %s is unsupported", field.getType().getName()));
    }

    private static String formatStringValue(String value) {
        return String.format("\"%s\"", value);
    }

}