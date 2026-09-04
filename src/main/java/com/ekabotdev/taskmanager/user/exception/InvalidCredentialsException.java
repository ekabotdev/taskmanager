package com.ekabotdev.taskmanager.user.exception;

import com.ekabotdev.taskmanager.task.exception.ResourceAlreadyExistsException;

public class InvalidCredentialsException extends ResourceAlreadyExistsException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
