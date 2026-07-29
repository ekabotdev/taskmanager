package com.ekabotdev.taskmanager.exception.globalexception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorsResponse {
    private LocalDateTime timestamp;

    private int status;

    private String error;

    private String message;

    private String path;
}
