package com.ekabotdev.taskmanager.task.specification;

import com.ekabotdev.taskmanager.task.entity.Task;
import com.ekabotdev.taskmanager.task.enums.TaskPriority;
import com.ekabotdev.taskmanager.task.enums.TaskStatus;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class TaskSpecification {

    public static Specification<Task> search (String search) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.or(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + search.toLowerCase() + "%"
                        ),
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("description")),
                                "%" + search.toLowerCase() + "%"
                        )
                )
        );

    }


    public static Specification<Task> hasStatus (TaskStatus status) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("status"),
                        status));
    }


    public static Specification<Task> hasPriority
            (TaskPriority taskPriority) {

        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("priority"),
                        taskPriority
                ));
    }

    public static Specification<Task> belongsToUser (Long userId) {
        return ((root, query, criteriaBuilder) ->

                criteriaBuilder.equal(root.get("user").get("id"), userId));

    }

    public static Specification<Task> isOverdue() {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.lessThan(
                                root.get("dueDate"),
                                criteriaBuilder.currentTimestamp()
                        ),
                        criteriaBuilder.notEqual(
                                root.get("status"),
                                TaskStatus.COMPLETED
                        )
                ));
    }

    public static Specification<Task> dueBefore (LocalDateTime date) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("dueDate"),
                        date
                ));
    }

    public static Specification<Task> dueAfter (LocalDateTime date) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("dueDate"),
                        date));
    }
}
