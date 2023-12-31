package org.taskifyapp.model.dto.request;


import lombok.Data;
import org.taskifyapp.model.enums.TaskStatus;

import java.time.LocalDateTime;

@Data
public class TaskCreationRequest {
    private String taskTitle;

    private String taskDescription;

    private Long receiverId;

    private LocalDateTime taskDeadline;

    private TaskStatus taskStatus;
}
