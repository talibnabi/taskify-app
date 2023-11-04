package org.taskifyapp.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.taskifyapp.model.dto.request.TaskCreationRequest;
import org.taskifyapp.model.dto.request.TaskUpdatingRequest;
import org.taskifyapp.model.dto.response.TaskResponse;
import org.taskifyapp.service.TaskService;

import java.util.List;


@RestController
@RequestMapping("/api/task")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/for-admin/create")
    public void createTask(@Valid @RequestBody TaskCreationRequest taskCreationRequest) {
        taskService.create(taskCreationRequest);
    }

    @DeleteMapping("/for-admin/{taskId}")
    public void deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.delete(taskId);
    }

    @PutMapping("/for-admin/update")
    public void updateTask(@RequestBody TaskUpdatingRequest taskUpdatingRequest) {
        taskService.update(taskUpdatingRequest);
    }


    @GetMapping("/for-user/{id}")
    public TaskResponse getTaskById(@PathVariable("id") Long id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/for-user/{userId}")
    public List<TaskResponse> getAllUserTaskList(@PathVariable("userId") Long userId) {
        return taskService.getAllTaskList(userId);
    }
}