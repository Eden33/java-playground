package com.eden33.reflection.model;

public class PermissionException extends Exception {
    private static final long serialVersionUID = 1L;

    public PermissionException() {
        super("Permission Denied");
    }

}
