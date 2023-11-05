package org.taskifyapp.service;

import org.taskifyapp.model.dto.request.TaskCreationRequest;
import org.taskifyapp.model.dto.request.TaskUpdatingRequest;
import org.taskifyapp.model.dto.response.TaskResponse;

import java.util.List;


public interface TaskService {


    TaskResponse getTaskById(Long id);

    List<TaskResponse> getAllTaskList(Long userId);

    void update(TaskUpdatingRequest request);

    void create(TaskCreationRequest request);

    void delete(Long id);
}
