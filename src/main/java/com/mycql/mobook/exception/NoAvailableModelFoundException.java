package com.mycql.mobook.exception;

public class NoAvailableModelFoundException extends RuntimeException {

    public NoAvailableModelFoundException() {
        super("There is currently no phone available with matching specs in inventory.");
    }
}
