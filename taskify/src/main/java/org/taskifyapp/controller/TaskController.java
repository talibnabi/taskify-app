package org.taskifyapp.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> createTask(@Valid @RequestBody TaskCreationRequest taskCreationRequest) {
        taskService.create(taskCreationRequest);
        return ResponseEntity.ok("Task created successfully!");
    }

    @DeleteMapping("/for-admin/delete/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.delete(taskId);
        return ResponseEntity.ok("Task deleted successfully!");
    }

    @PutMapping("/for-admin/update")
    public ResponseEntity<String> updateTask(@RequestBody TaskUpdatingRequest taskUpdatingRequest) {
        taskService.update(taskUpdatingRequest);
        return ResponseEntity.ok("Task updated successfully!");
    }


    @GetMapping("/for-user/get-task/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping("/for-user/get-all-task/{id}")
    public ResponseEntity<List<TaskResponse>> getAllUserTaskList(@PathVariable("id") Long id) {
        return ResponseEntity.ok(taskService.getAllTaskList(id));
    }
}