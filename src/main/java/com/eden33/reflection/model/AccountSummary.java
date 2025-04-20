package com.eden33.reflection.model;

public class AccountSummary {
    private final String firstName;
    private final String lastName;
    private final short age;
    private final int salary;

    public AccountSummary(String firstName, String lastName, short age, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public short getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }
}
