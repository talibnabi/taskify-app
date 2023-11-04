package org.taskifyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.taskifyapp.model.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findTaskById(Long id);

    @Query("SELECT t FROM Task t JOIN t.userAssigners u WHERE u.id = :userId")
    List<Task> findTasksByUserId(@Param("userId") Long userId);

}
