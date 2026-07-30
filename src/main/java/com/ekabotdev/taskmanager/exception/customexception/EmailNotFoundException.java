package com.ekabotdev.taskmanager.exception.customexception;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException(String message){
        super(message);
    }
}
