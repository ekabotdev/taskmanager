package com.ekabotdev.taskmanager.controller;


import com.ekabotdev.taskmanager.dto.task.CreateTaskRequest;
import com.ekabotdev.taskmanager.dto.task.TaskResponse;
import com.ekabotdev.taskmanager.service.TaskService.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody CreateTaskRequest request,
                                                   Authentication authentication) {
        String authenticatedEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        TaskResponse response = taskService.createTask(request, authenticatedEmail);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
