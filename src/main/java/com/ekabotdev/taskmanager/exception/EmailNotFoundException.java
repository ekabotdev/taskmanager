package com.ekabotdev.taskmanager.exception;

public class EmailNotFoundException  extends ResourceAlreadyExistsException{
    public EmailNotFoundException(String message){
        super(message);
    }
}
