package com.ekabotdev.taskmanager.service.TaskService;

import com.ekabotdev.taskmanager.dto.auth.UserResponse;
import com.ekabotdev.taskmanager.dto.task.CreateTaskRequest;
import com.ekabotdev.taskmanager.dto.task.PageResponse;
import com.ekabotdev.taskmanager.dto.task.TaskResponse;
import com.ekabotdev.taskmanager.dto.task.UpdateTaskRequest;
import com.ekabotdev.taskmanager.dto.user.UserSummaryResponse;
import com.ekabotdev.taskmanager.entity.Task;
import com.ekabotdev.taskmanager.entity.User;
import com.ekabotdev.taskmanager.exception.customexception.ResourceNotFoundException;
import com.ekabotdev.taskmanager.repository.TaskRepository;
import com.ekabotdev.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(readOnly = true)
    public PageResponse<TaskResponse> getTasks (String authenticatedEmail, Pageable pageable) {

        User user = userRepository.findByEmail(authenticatedEmail).orElseThrow(() ->
                new ResourceNotFoundException("Authenticated user is not found.")
        );
        Page<Task>  taskPage = taskRepository.findAllByUser_Id(user.getId(),pageable);

        List<TaskResponse> content = taskPage.getContent()
                .stream()
                .map(this::mapToResponse)
                .toList();

        return new PageResponse<>(
                content,
                taskPage.getNumber(),
                taskPage.getSize(),
                taskPage.getTotalElements(),
                taskPage.getTotalPages(),
                taskPage.isFirst(),
                taskPage.isLast()
        );
    }



    @Transactional(readOnly = true)
    public TaskResponse getTasksById (  Long taskId, String authenticatedEmail) {
        User user = userRepository.findByEmail(authenticatedEmail).orElseThrow(() ->
         new ResourceNotFoundException("Authenticated user is not found."));

        Task task = taskRepository.findByIdAndUser_Id(taskId,user.getId()).orElseThrow(() ->
                new ResourceNotFoundException("Task not found."));

        Task savedTask = taskRepository.save(task);

        return mapToResponse(savedTask);

    }



    @Transactional
    public TaskResponse updateTask (Long taskId,
                                    UpdateTaskRequest request,
                                    String authenticatedEmail) {

        User user = userRepository.findByEmail(authenticatedEmail).orElseThrow(() ->
                new ResourceNotFoundException("Authenticated user is not found."));

        Task task = taskRepository.findByIdAndUser_Id(taskId,user.getId()).orElseThrow(() ->
        new ResourceNotFoundException("Task not found."
              )
        );

        if (request.getTitle() != null) {
            task.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            task.setDescription(request.getDescription());
        }
        if (request.getStatus() != null) {
            task.setTaskStatus(request.getStatus());
        }
        if (request.getPriority() != null) {
            task.setTaskPriority(request.getPriority());
        }
        if (request.getDueDate() != null) {
            task.setDueDate(request.getDueDate());
        }
        return mapToResponse(task);

    }
}
