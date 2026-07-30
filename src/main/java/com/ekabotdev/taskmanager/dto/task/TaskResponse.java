package com.ekabotdev.taskmanager.dto.task;

import com.ekabotdev.taskmanager.dto.user.UserSummaryResponse;
import com.ekabotdev.taskmanager.enums.TaskPriority;
import com.ekabotdev.taskmanager.enums.TaskStatus;
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
