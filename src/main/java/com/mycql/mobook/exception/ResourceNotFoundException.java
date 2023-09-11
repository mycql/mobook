package com.mycql.mobook.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("No matching resource found.");
    }
}
