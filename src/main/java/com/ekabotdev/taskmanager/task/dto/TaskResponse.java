package com.ekabotdev.taskmanager.task.dto;

import com.ekabotdev.taskmanager.user.dto.UserSummaryResponse;
import com.ekabotdev.taskmanager.task.enums.TaskPriority;
import com.ekabotdev.taskmanager.task.enums.TaskStatus;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserSummaryResponse response;

}
