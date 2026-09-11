package com.ekabotdev.taskmanager.task.service;

import com.ekabotdev.taskmanager.task.dto.CreateTaskRequest;
import com.ekabotdev.taskmanager.task.dto.PageResponse;
import com.ekabotdev.taskmanager.task.dto.TaskResponse;
import com.ekabotdev.taskmanager.task.dto.UpdateTaskRequest;
import com.ekabotdev.taskmanager.task.enums.TaskPriority;
import com.ekabotdev.taskmanager.task.enums.TaskStatus;
import com.ekabotdev.taskmanager.task.specification.TaskSpecification;
import com.ekabotdev.taskmanager.user.dto.UserSummaryResponse;
import com.ekabotdev.taskmanager.task.entity.Task;
import com.ekabotdev.taskmanager.user.entity.User;
import com.ekabotdev.taskmanager.task.exception.ResourceNotFoundException;
import com.ekabotdev.taskmanager.task.repository.TaskRepository;
import com.ekabotdev.taskmanager.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

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
    public PageResponse<TaskResponse> getTasks (
            TaskStatus status,
            TaskPriority priority,
            String search,
            String authenticatedEmail,
             Pageable pageable) {


        Set<String> allowedSortFields = Set.of(
                "id",
                "title",
                "priority",
                "status",
                "dueDate",
                "createdAt",
                "updatedAt"
        );

        for (Sort.Order order : pageable.getSort()) {

            if (!allowedSortFields.contains(order.getProperty())) {
                throw new IllegalArgumentException(
                        "Sorting by '" + order.getProperty() + "' is not supported."
                );
            }
        }


        User user = userRepository.findByEmail(authenticatedEmail).orElseThrow(() ->
                new ResourceNotFoundException("Authenticated user is not found.")
        );

        Specification<Task> specification = TaskSpecification.belongsToUser(user.getId());

        if (status != null) {
            specification = specification.and(
                    TaskSpecification.hasStatus(status)
            );
        }

        if (priority != null) {
            specification = specification.and(
                    TaskSpecification.hasPriority(priority)
            );
        }

        if  (search != null && !search.isBlank()) {
          specification = specification.and(
                  TaskSpecification.search(search.trim())
          );
        }


        Page<Task>  taskPage = taskRepository.findAll(specification, pageable);

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

    @Transactional
    public void deleteTask(Long taskId, String authenticatedEmail) {
        User user = userRepository.findByEmail(authenticatedEmail).orElseThrow(() ->
                new ResourceNotFoundException("Authenticated user is not found."
                )
        );

        Task task = taskRepository.findByIdAndUser_Id(taskId,user.getId()).orElseThrow(() ->
        new ResourceNotFoundException("Task not found."
            )
        );
        taskRepository.delete(task);

    }

    @Transactional(readOnly = true)
    public PageResponse<TaskResponse> getOverdueTasks (String authenticatedEmail, Pageable pageable) {
        User user = userRepository.findByEmail(authenticatedEmail).orElseThrow(()
                -> new ResourceNotFoundException("Authenticated user is not found."));

        Specification<Task> specification = TaskSpecification.belongsToUser(user.getId())
                .and(TaskSpecification.isOverdue());

        Page<Task> taskPage = taskRepository.findAll(specification, pageable);

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
}
