package com.mycql.mobook.exception;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException() {
        super("This booking client is not registered and cannot make transactions.");
    }
}
