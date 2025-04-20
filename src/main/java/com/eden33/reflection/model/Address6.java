package com.eden33.reflection.model;
public class Address6 {
    private static final int ZIP_CODE_MAX_DIGITS = 5;
    private final transient String zipCode;
    private final String street;
    private final short apartment;
 
    public Address6(String street, short apartment, String zipCode) {
        this.street = street;
        this.apartment = apartment;
        this.zipCode = zipCode;
    }
}