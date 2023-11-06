package org.taskifyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.query.Param;
import org.taskifyapp.model.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findTaskById(Long id);

    Optional<List<Task>> findTasksByReceiverId(@Param("userId") Long userId);

}
