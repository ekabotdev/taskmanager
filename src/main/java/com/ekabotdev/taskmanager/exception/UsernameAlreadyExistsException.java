package com.ekabotdev.taskmanager.exception;

public class UsernameAlreadyExistsException extends ResourceAlreadyExistsException{
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }

}
