package com.ekabotdev.taskmanager.task.exception;

public class ResourceAlreadyExistsException  extends RuntimeException{
    public ResourceAlreadyExistsException(String message){
        super(message);
    }
}
