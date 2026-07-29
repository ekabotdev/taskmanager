package com.ekabotdev.taskmanager.exception.customexception;

public class InvalidCredentialsException extends ResourceAlreadyExistsException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
