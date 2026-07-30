package com.ekabotdev.taskmanager.controller;


import com.ekabotdev.taskmanager.dto.task.CreateTaskRequest;
import com.ekabotdev.taskmanager.dto.task.PageResponse;
import com.ekabotdev.taskmanager.dto.task.TaskResponse;
import com.ekabotdev.taskmanager.service.TaskService.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping
    public ResponseEntity<PageResponse<TaskResponse>> getAllTasks(Authentication authentication,
                                                                  Pageable pageable) {
        String authenticatedEmail = authentication.getName();

        PageResponse<TaskResponse> response = taskService.getTasks(authenticatedEmail, pageable);

        return ResponseEntity.ok(response);
    }
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponse> getTaskById(  @PathVariable Long taskId, Authentication authentication) {
        String authenticatedEmail = authentication.getName();
        TaskResponse response = taskService.getTasksById( taskId , authenticatedEmail);
        return ResponseEntity.ok(response);
    }
}
