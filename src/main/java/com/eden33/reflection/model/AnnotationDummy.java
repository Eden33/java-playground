package com.eden33.reflection.model;

import com.eden33.reflection.AnnotationUtil.MethodMarker;

public class AnnotationDummy {

    int foo() { return 0; }

    @MethodMarker(order = 2)
    void a() {}

    @MethodMarker(order = 4)
    void day(int value) {}

    @MethodMarker(order = 3)
    void great() {}

    void bar() {}

    @MethodMarker(order = 1)
    void have() {}
}
