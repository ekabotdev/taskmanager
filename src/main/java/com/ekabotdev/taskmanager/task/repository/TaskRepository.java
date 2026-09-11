package com.ekabotdev.taskmanager.task.repository;

import com.ekabotdev.taskmanager.task.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long>
        , JpaSpecificationExecutor<Task> {
    Optional<Task> findByIdAndUser_Id(Long taskId, Long userId);
    Page<Task> findAllByUser_Id(Long userId, Pageable pageable);

}
