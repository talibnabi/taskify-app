package org.taskifyapp.model.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskUpdatingRequest {

    @NotNull
    private Long taskId;

    private String taskTitle;

    private String taskDescription;

    private Long receiverId;

    private LocalDateTime deadline;
}
