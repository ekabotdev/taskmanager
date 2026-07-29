package com.ekabotdev.taskmanager.exception;

public class InvalidCredentialsException extends ResourceAlreadyExistsException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
