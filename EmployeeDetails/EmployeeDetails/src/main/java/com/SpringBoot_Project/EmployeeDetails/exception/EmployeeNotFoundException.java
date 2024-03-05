package com.SpringBoot_Project.EmployeeDetails.exception;


public class EmployeeNotFoundException extends RuntimeException {

    
    public EmployeeNotFoundException(String message) {
        super(message);

    }
}