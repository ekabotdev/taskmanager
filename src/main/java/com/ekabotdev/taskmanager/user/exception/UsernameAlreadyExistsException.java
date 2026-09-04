package com.ekabotdev.taskmanager.user.exception;

import com.ekabotdev.taskmanager.task.exception.ResourceAlreadyExistsException;

public class UsernameAlreadyExistsException extends ResourceAlreadyExistsException {
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }

}
