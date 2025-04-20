package com.eden33.reflection;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ArrayReader class.
 */
public class ArrayReaderTest {

    private final ArrayReader arrayReader = new ArrayReader();

    @Test
    public void testGetArrayElementWithPositiveIndex() {
        Integer[] array = {1, 2, 3, 4, 5};
        assertEquals(3, arrayReader.getArrayElement(array, 2));
    }

    @Test
    public void testGetArrayElementWithNegativeIndex() {
        Integer[] array = {1, 2, 3, 4, 5};
        assertEquals(4, arrayReader.getArrayElement(array, -2));
    }

    @Test
    public void testGetArrayElementWithInvalidPositiveIndex() {
        Integer[] array = {1, 2, 3, 4, 5};
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> arrayReader.getArrayElement(array, 5));
    }

    @Test
    public void testGetArrayElementWithInvalidNegativeIndex() {
        Integer[] array = {1, 2, 3, 4, 5};
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> arrayReader.getArrayElement(array, -6));
    }

    @Test
    public void testGetArrayElementWithNullArray() {
        assertThrows(IllegalArgumentException.class, () -> arrayReader.getArrayElement(null, 0));
    }

    @Test
    public void testGetArrayElementWithNonArrayObject() {
        String notAnArray = "Not an array";
        assertThrows(IllegalArgumentException.class, () -> arrayReader.getArrayElement(notAnArray, 0));
    }
}