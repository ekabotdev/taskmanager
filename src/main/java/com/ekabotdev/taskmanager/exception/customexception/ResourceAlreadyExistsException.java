package com.ekabotdev.taskmanager.exception.customexception;

public class ResourceAlreadyExistsException  extends RuntimeException{
    public ResourceAlreadyExistsException(String message){
        super(message);
    }
}
