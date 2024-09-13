package com.lms.enrollment.exception;

/**
 * Custom exception to indicate that a requested resource was not found.
 */
public class NotFoundException extends RuntimeException {
    /**
     * Constructs a new NotFoundException with the specified detail message.
     */
    public NotFoundException(String message) {
        super(message);
    }
}
