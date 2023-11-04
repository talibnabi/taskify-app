package org.taskifyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taskifyapp.model.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
