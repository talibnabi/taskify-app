package org.taskifyapp.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.taskifyapp.model.enums.TaskStatus;
import org.taskifyapp.model.interfaces.TaskManager;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task_taskify")
public class Task implements TaskManager, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "task_sequence", sequenceName = "task_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_sequence")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_status")
    private TaskStatus taskStatus;

    @ManyToMany(mappedBy = "userTasks")
    private Set<User> userAssigners;

    @Override
    public Integer getTaskStatusWithId() {
        return this.taskStatus.getId();
    }

    @Override
    public void setTaskStatusWithId(Integer taskStatusId) {
        this.taskStatus = TaskStatus.fromId(taskStatusId);
    }
}
