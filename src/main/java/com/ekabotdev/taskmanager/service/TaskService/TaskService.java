package com.ekabotdev.taskmanager.service.TaskService;

import com.ekabotdev.taskmanager.dto.auth.UserResponse;
import com.ekabotdev.taskmanager.dto.task.CreateTaskRequest;
import com.ekabotdev.taskmanager.dto.task.TaskResponse;
import com.ekabotdev.taskmanager.dto.user.UserSummaryResponse;
import com.ekabotdev.taskmanager.entity.Task;
import com.ekabotdev.taskmanager.entity.User;
import com.ekabotdev.taskmanager.exception.customexception.ResourceNotFoundException;
import com.ekabotdev.taskmanager.repository.TaskRepository;
import com.ekabotdev.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {
    public final TaskRepository taskRepository;
    public final UserRepository userRepository;

    public TaskResponse createTask(CreateTaskRequest createTaskRequest,
                                   String authenticatedEmail) {

        User user = userRepository.findByEmail(authenticatedEmail).orElseThrow(() ->
                new ResourceNotFoundException("Authenticated user is not found."));

        Task task = new Task();
        task.setTitle(createTaskRequest.getTitle());
        task.setDescription(createTaskRequest.getDescription());
        task.setTaskStatus(createTaskRequest.getTaskStatus());
        task.setTaskPriority(createTaskRequest.getPriority());
        task.setDueDate(createTaskRequest.getDueDate());

        task.setUser(user);

        Task savedTask = taskRepository.save(task);

        return mapToResponse(savedTask);
    }
    private TaskResponse mapToResponse(Task task) {
        User user = task.getUser();

        UserSummaryResponse userResponse = new UserSummaryResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
        return  new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getTaskStatus(),
                task.getTaskPriority(),
                task.getDueDate(),
                task.getCreatedAt(),
                task.getUpdatedAt(),
                userResponse
        );
    }
}
