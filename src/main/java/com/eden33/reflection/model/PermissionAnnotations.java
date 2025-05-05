package com.eden33.reflection.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


public class PermissionAnnotations {

    /** 
     * This annotation can be applied to classes multiple times.
     * It uses the `@Repeatable` annotation to allow multiple instances of 
     * that annotation to be defined on a single class.
    */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Repeatable(Permissions.class)
    public @interface Permission {
        Role role();
        OperationType[] allowed();
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Permissions {
        Permission[] value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface MethodOperations {
        OperationType[] value();
    }
    
    public enum Role {
        CLERK, MANAGER, SUPPORT_ENGINEER
    }
    
    public enum OperationType {
        READ, WRITE, DELETE
    }
}