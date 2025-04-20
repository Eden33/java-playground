package com.eden33.reflection;

import java.lang.reflect.Array;

/**
 * A utility class to read elements from an array using reflection.
 * <p>
 * Code was written when attended the Udemy course "Advanced Java Topics: Java Reflection - Master Class" by Michael Pogrebinsky.
 * <p>
 * Course chapter: "Section 3: Inspection of Fields & Arrays"
 */
public class ArrayReader {

    /**
     * Retrieves an element from the specified array at the given index.
     * <p>
     * This method supports both positive and negative indices. A positive index
     * retrieves the element at the specified position from the start of the array.
     * A negative index retrieves the element at the specified position from the
     * end of the array, where -1 represents the last element, -2 represents the
     * second-to-last element, and so on.
     * </p>
     *
     * @param array the array from which to retrieve the element. Must not be null
     *              and must be an actual array.
     * @param index the index of the element to retrieve. Can be positive or
     *              negative. Negative indices count from the end of the array.
     * @return the element at the specified index in the array.
     * @throws IllegalArgumentException      if the provided object is not an array
     *                                       or if the array is null.
     * @throws ArrayIndexOutOfBoundsException if the index is out of bounds for the
     *                                       array.
     */
    public Object getArrayElement(Object array, int index) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Provided object is not an array");
        }
        int length = Array.getLength(array);
        if (index < 0) {
            index = length + index;
        }
        if (index < 0 || index >= length) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds for array length: " + length);
        }
        return Array.get(array, index);
    }
}