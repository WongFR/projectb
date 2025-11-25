package com.example.projectb.exception;

public class InsufficientUsersException extends RuntimeException {
    
    public InsufficientUsersException(String message) {
        super(message);
    }
    
    public InsufficientUsersException(String message, Throwable cause) {
        super(message, cause);
    }
}