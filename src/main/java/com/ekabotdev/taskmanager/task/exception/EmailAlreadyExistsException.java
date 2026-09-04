package com.ekabotdev.taskmanager.task.exception;

public class EmailAlreadyExistsException   extends ResourceAlreadyExistsException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
