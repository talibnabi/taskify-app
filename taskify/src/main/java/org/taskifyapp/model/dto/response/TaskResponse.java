package org.taskifyapp.model.dto.response;

import lombok.Data;
import org.taskifyapp.model.enums.TaskStatus;

import java.time.LocalDateTime;


@Data
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private Long senderId;
    private Long receiverId;
    private LocalDateTime deadline;
    private TaskStatus status;
}
