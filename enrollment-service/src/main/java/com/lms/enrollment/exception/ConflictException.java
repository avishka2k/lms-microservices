package com.lms.enrollment.exception;

/**
 * Custom exception to indicate a conflict scenario in the application.
 */
public class ConflictException extends RuntimeException{
    public ConflictException(String message) {
        super(message);
    }
}
