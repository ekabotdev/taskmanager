package com.ekabotdev.taskmanager.task.exception;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException(String message){
        super(message);
    }
}
