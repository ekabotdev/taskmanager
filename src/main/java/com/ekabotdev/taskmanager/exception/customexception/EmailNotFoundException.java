package com.ekabotdev.taskmanager.exception.customexception;

public class EmailNotFoundException  extends ResourceAlreadyExistsException {
    public EmailNotFoundException(String message){
        super(message);
    }
}
