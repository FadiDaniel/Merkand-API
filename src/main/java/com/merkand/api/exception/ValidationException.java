package com.merkand.api.exception;

/**
 * Exception thrown when validation fails on input data.
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}