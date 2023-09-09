package com.mycql.mobook.exception;

public class NoAvailableModelFoundException extends RuntimeException {

    public NoAvailableModelFoundException() {
        super("There is currently no available phones in inventory.");
    }
}
