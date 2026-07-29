package com.ekabotdev.taskmanager.repository;

import com.ekabotdev.taskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByIdAndUser_Id(Long taskId, Long userId);
    List<Task> findAllByUser_Id(Long userId);

}
