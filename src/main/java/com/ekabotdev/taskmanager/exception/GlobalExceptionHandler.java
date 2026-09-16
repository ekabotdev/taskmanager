package com.ekabotdev.taskmanager.exception;


import com.ekabotdev.taskmanager.task.exception.BadRequestException;
import com.ekabotdev.taskmanager.task.exception.ResourceAlreadyExistsException;
import com.ekabotdev.taskmanager.task.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorsResponse handleResourceAlreadyExists(ResourceAlreadyExistsException ex,
                                                      HttpServletRequest request) {
        return new ErrorsResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleValidationErrorException(MethodArgumentNotValidException ex,
                                                                  HttpServletRequest request) {
        Map<String, String> errors = new LinkedHashMap<>();
        for(FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ValidationErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errors,
                request.getRequestURI()
        );
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsResponse handleResourceNotFoundException
            (ResourceNotFoundException ex,
             HttpServletRequest request) {

        return new ErrorsResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
    }
    
    @ExceptionHandler(BadRequestException.class)
    public ErrorsResponse handleBadRequestException
            (BadRequestException ex, HttpServletRequest request) {
        
        return new ErrorsResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(), 
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
    }
}
