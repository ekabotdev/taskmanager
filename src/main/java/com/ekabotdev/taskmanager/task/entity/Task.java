package com.ekabotdev.taskmanager.task.entity;

import com.ekabotdev.taskmanager.task.enums.TaskPriority;
import com.ekabotdev.taskmanager.task.enums.TaskStatus;
import com.ekabotdev.taskmanager.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100 ,message = "Title must be under 100 characters")
    @Column(nullable = false, length = 100)
    private String title;

    @NotBlank
    @Size(max = 1000, message = "Description must be under 1000 characters")
    @Column(nullable = false, length = 1000)
    private String description;

    @NotNull
    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @NotNull
    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private TaskPriority taskPriority;

    @NotNull
    @Column( name = "due_date",nullable = false)
    private LocalDateTime dueDate;

    @Column(name = "created_at",nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column( name = "updated_at",nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
