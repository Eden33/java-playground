package com.eden33.reflection;

import java.util.*;
import com.eden33.reflection.model.User;
import com.eden33.reflection.model.PermissionAnnotations.MethodOperations;
import com.eden33.reflection.model.PermissionAnnotations.Permission;
import com.eden33.reflection.model.PermissionAnnotations.OperationType;
import com.eden33.reflection.model.PermissionException;
import java.lang.reflect.*;

/**
 * A permission checker that works with repeatable permission annotations.
 * <p>
 * This class provides methods to verify if a logged-in user has the necessary permissions
 * to execute specific operations based on annotations defined in the code.
 * <p>
 * The {@link com.eden33.reflection.model.PermissionAnnotations.Permission} annotation is used
 * to define the permissions at the class level, while the {@link com.eden33.reflection.model.PermissionAnnotations.MethodOperations}
 * annotation is used to specify the operations allowed for a particular method.
 * <p>
 * Code was completed when attending the Udemy course "Advanced Java Topics: Java Reflection - Master Class" by Michael Pogrebinsky.
 * <p>
 * Course chapter: "Section 7: Annotations with Java Reflection"
 */
public class PermissionsChecker {

    /**
     * Checks that the logged-in user in the callerObject has the right permissions to perform the operations
     * in the caller method.
     * Throws PermissionException if the user is not authorized to perform those operations based on the user's
     * role
     */
    public static void checkPermissions(Object callerObject, String callerMethodName) throws Throwable {
        User user = getLoggedInUser(callerObject);
        Method callingMethod = getCallingMethod(callerObject, callerMethodName);
        Permission[] allPermissions = getClassAnnotatedPermissions(callerObject);
        MethodOperations methodOperations = getCallerMethodOperations(callingMethod);

        OperationType[] methodOperationTypes = methodOperations.value();

        List<OperationType> userAllowedOperations = findUserAllowedOperations(allPermissions, user);

        for (OperationType methodOperationsTypes : methodOperationTypes) {
            if (!userAllowedOperations.contains(methodOperationsTypes)) {
                throw new PermissionException();
            }
        }
    }

    /**
     * Returns a List of all OperationTypes that the logged-in user is allowed to perform
     * If the user has no permissions in the allPermissions, an empty list should be returned
     */ 
    static List<OperationType> findUserAllowedOperations(Permission[] allPermissions, User user) {
        List<OperationType> userAllowedOperations = new ArrayList<>();

        for (Permission permission : allPermissions) {
            if (permission.role() == user.getRole()) {
                userAllowedOperations.addAll(Arrays.asList(permission.allowed()));
            }
        }

        return userAllowedOperations;
    }

    /**
     * Returns all the Permissions annotations the the callerObject's class is annotated with
     */ 
    static Permission[] getClassAnnotatedPermissions(Object callerObject) {
        Permission[] permissions = callerObject.getClass().getAnnotationsByType(Permission.class);
        return permissions;
    }

    /**
     * Returns the MethodOperations annotation that the callerMethod is annotated with
     */ 
    static MethodOperations getCallerMethodOperations(Method callerMethod) {
        MethodOperations methodOperations = callerMethod.getAnnotation(MethodOperations.class);
        if (methodOperations == null) {
            throw new IllegalStateException("The method is not annotated with MethodOperations");
        }
        return methodOperations;
    }

//********************* Helper Methods ******************************/

    /**
     * Returns the User object representing the logged-in user
     */ 
    private static User getLoggedInUser(Object callerObject) throws NoSuchFieldException, IllegalAccessException {
        Class<?> callerClass = callerObject.getClass();

        Field userField = callerClass.getDeclaredField("user");

        userField.setAccessible(true);

        if (!userField.getType().equals(User.class)) {
            throw new IllegalStateException("The caller object must have a user field of type User");
        }

        return (User) userField.get(callerObject);
    }
    
    /**
     * Returns the Method object of the callerObject's class corresponding to the methodName
     */
    private static Method getCallingMethod(Object callerObject, String methodName) {
        return Arrays.stream(callerObject.getClass().getDeclaredMethods())
                .filter(method -> method.getName().equals(methodName))
                .findFirst()
                .orElseThrow(() 
                        -> new IllegalStateException(String.format("The passed method name :%s does not exist", methodName)));
    }
}