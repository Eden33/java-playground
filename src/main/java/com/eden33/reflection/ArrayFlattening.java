package com.eden33.reflection;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility class that performs "smart concatenation" of elements.
 * <p>
 * Code was written when attended the Udemy course "Advanced Java Topics: Java Reflection - Master Class" by Michael Pogrebinsky.
 * <p>
 * Course chapter: "Section 4: Field Modification & Arrays Creation"
 */
public class ArrayFlattening {

    /**
     * Concatenates a variable number of arguments.
     * 
     * The arguments can be of:
     * <ul>
     * <li>Some type T</li>
     * <li>An array of type T</li>
     * <li>A combination of arrays and elements of type T</li>
     * </ul>
     * 
     * The implementation always assumes that the given type as the first argument is assignable
     * from any element given as an input to the method.
     * 
     * @return A concatenated array of type T
     */
    @SuppressWarnings("unchecked")
    public <T> T concat(Class<?> componentType, Object... arguments) {
        List<Object> elements = new ArrayList<>();

        for (Object arg : arguments) {
            if (arg == null)
                continue;
            if (arg.getClass().isArray()) {
                int len = Array.getLength(arg);
                for (int i = 0; i < len; i++) {
                    elements.add(Array.get(arg, i));
                }
            } else {
                elements.add(arg);
            }
        }

        Object result = Array.newInstance(componentType, elements.size());
        for (int i = 0; i < elements.size(); i++) {
            Array.set(result, i, elements.get(i));
        }

        return (T) result;
    }
}
