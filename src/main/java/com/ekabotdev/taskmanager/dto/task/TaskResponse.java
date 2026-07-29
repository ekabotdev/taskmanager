package com.ekabotdev.taskmanager.dto.task;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ekabotdev.taskmanager.dto.user.UserSummaryResponse;
import com.ekabotdev.taskmanager.enums.TaskPriority;
import com.ekabotdev.taskmanager.enums.TaskStatus;
import lombok.Value;
import java.time.LocalDateTime;

@Value
public class TaskResponse {

    Long id;
    String title;
    String description;
    TaskStatus status;
    TaskPriority priority;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime dueDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime updatedAt;

    UserSummaryResponse user;
}

