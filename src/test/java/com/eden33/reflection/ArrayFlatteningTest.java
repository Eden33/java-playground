package com.eden33.reflection;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayFlatteningTest {

    @Test
    public void testConcatWithIntegers() {
        ArrayFlattening arrayFlattening = new ArrayFlattening();
        Integer[] result = arrayFlattening.concat(Integer.class, 1, 2, 3, 4, 5);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, result);
    }

    @Test
    public void testConcatWithPrimitiveInts() {
        ArrayFlattening arrayFlattening = new ArrayFlattening();
        int[] result = arrayFlattening.concat(int.class, 1, 2, 3, new int[]{4, 5, 6}, 7);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7}, result);
    }

    @Test
    public void testConcatWithStrings() {
        ArrayFlattening arrayFlattening = new ArrayFlattening();
        String[] result = arrayFlattening.concat(String.class, new String[]{"a", "b"}, "c", new String[]{"d", "e"});
        assertArrayEquals(new String[]{"a", "b", "c", "d", "e"}, result);
    }
}