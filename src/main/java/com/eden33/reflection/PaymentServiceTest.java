package com.eden33.reflection;

/**
 * Represents a test suite for testing the PaymentService
 */
public class PaymentServiceTest {
    //private PaymentService service;
    
    public static void beforeClass() {
        System.out.println("static #beforeClass called at the beginning of the test suite execution");
    }
    
    public void setupTest() {
        System.out.println("#setupTest called before every test method execution");
    }
    
    public void testCreditCardPayment() {
        System.out.println("#testCreditCardPayment called");
    }
    
    public void testWireTransfer() {
        System.out.println("#testWireTransfer called");
    }
    
    public void testInsufficientFunds() {
        System.out.println("#testInsufficientFunds called");
    }
    
    public static void afterClass() {
        System.out.println("static #afterClass called at the end of the test suite execution");
    }

    public void shouldBeIgnored() {
        System.out.println("This method should be ignored");
    }

    public int testMethodWithReturnType() {
        System.out.println("This method should be ignored");
        return 0;
    }

    public void testMethodWithParameters(int a, int b) {
        System.out.println("This method should be ignored");
    }
}