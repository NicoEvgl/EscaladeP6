package org.nico.model.exception;

public class UserBlockedException extends Exception {
    public UserBlockedException(){}

    public UserBlockedException(String errorDescription){
        super(errorDescription);
    }
}
