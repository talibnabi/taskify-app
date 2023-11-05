package org.taskifyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.taskifyapp.model.entity.Task;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findTaskById(Long id);

}
