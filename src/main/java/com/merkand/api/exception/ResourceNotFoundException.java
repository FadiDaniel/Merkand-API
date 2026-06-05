package com.merkand.api.exception;

/**
 * Exception thrown when a resource is not found in the system.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}