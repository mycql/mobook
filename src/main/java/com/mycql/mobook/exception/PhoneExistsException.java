package com.mycql.mobook.exception;

public class PhoneExistsException extends RuntimeException {

    public PhoneExistsException() {
        super("This phone is already on hand at the store");
    }
}
