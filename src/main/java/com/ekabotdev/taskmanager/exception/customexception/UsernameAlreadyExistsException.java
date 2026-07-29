package com.ekabotdev.taskmanager.exception.customexception;

public class UsernameAlreadyExistsException extends ResourceAlreadyExistsException {
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }

}
