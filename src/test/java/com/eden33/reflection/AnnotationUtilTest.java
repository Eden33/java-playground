package com.eden33.reflection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.eden33.reflection.model.AnnotationDummy;

public class AnnotationUtilTest {

    @Test
    public void testGetAllAnnotatedMethods() {
        AnnotationDummy dummy = new AnnotationDummy();
        
        List<Method> annotatedMethods = AnnotationUtil.getAllAnnotatedMethodsOrdered(dummy);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < annotatedMethods.size(); i++) {
            Method method = annotatedMethods.get(i);
            sb.append(method.getName());
            if (i < annotatedMethods.size() - 1) {
                sb.append(" ");
            }
        }
        
        String actual = sb.toString();
        assertEquals("have a great day", actual);
    }
}