package com.ekabotdev.taskmanager.task.controller;


import com.ekabotdev.taskmanager.task.dto.CreateTaskRequest;
import com.ekabotdev.taskmanager.task.dto.PageResponse;
import com.ekabotdev.taskmanager.task.dto.TaskResponse;
import com.ekabotdev.taskmanager.task.dto.UpdateTaskRequest;
import com.ekabotdev.taskmanager.task.enums.TaskPriority;
import com.ekabotdev.taskmanager.task.enums.TaskStatus;
import com.ekabotdev.taskmanager.task.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
    public ResponseEntity<PageResponse<TaskResponse>> getAllTasks
            (
                    @RequestParam(required = false)TaskStatus status,
                    @RequestParam(required = false)TaskPriority priority,
                    @RequestParam(required = false) String search,
                    @RequestParam(required = false)LocalDateTime dueBefore,
                    @RequestParam(required = false) LocalDateTime dueAfter
                    , Authentication authentication,
                    Pageable pageable) {
        String authenticatedEmail = authentication.getName();

        PageResponse<TaskResponse> response = taskService.getTasks
                (
                        status,
                        priority,
                        search,
                        dueBefore,
                        dueAfter,
                        authenticatedEmail, pageable);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponse> getTaskById(  @PathVariable Long taskId, Authentication authentication) {
        String authenticatedEmail = authentication.getName();
        TaskResponse response = taskService.getTasksById( taskId , authenticatedEmail);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{taskId}")

    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long taskId,
                                                   @Valid @RequestBody UpdateTaskRequest updateTaskRequest,
                                                   Authentication authentication) {
        String authenticatedEmail = authentication.getName();

        TaskResponse taskResponse = taskService.updateTask(taskId,updateTaskRequest,authenticatedEmail);

        return ResponseEntity.ok(taskResponse);
    }
    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long taskId, Authentication authentication) {
        String authenticatedEmail = authentication.getName();
        taskService.deleteTask(taskId ,
                authenticatedEmail);
    }


    @GetMapping("/overdue")
    public ResponseEntity<PageResponse<TaskResponse>> getOverdueTasks
            ( Authentication authentication, Pageable pageable) {
        String authenticatedEmail = authentication.getName();

        PageResponse<TaskResponse> response = taskService.getOverdueTasks
                (authenticatedEmail, pageable);

        return ResponseEntity.ok(response);
    }
}
