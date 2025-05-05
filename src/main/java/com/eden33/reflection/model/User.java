package com.eden33.reflection.model;

import com.eden33.reflection.model.PermissionAnnotations.Role;

public class User {
    private final String name;
    private final Role role;
 
    public User(String name, Role role) {
        this.name = name;
        this.role = role;
    }
 
    public Role getRole() {
        return role;
    }

    public String getName() {
        return name;
    }
}