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


    /**
     * Create a new task.
     *
     * @param taskCreationRequest The request data for creating a task.
     * @return ResponseEntity with a success message upon task creation.
     */
    @PostMapping("/for-admin/create")
    public ResponseEntity<String> createTask(@Valid @RequestBody TaskCreationRequest taskCreationRequest) {
        taskService.create(taskCreationRequest);
        return ResponseEntity.ok("Task created successfully!");
    }


    /**
     * Delete a task by its ID.
     *
     * @param taskId The ID of the task to delete.
     * @return ResponseEntity with a success message upon task deletion.
     */
//    @DeleteMapping("/for-admin/delete/{taskId}")
//    public ResponseEntity<String> deleteTask(@PathVariable("taskId") Long taskId) {
//        taskService.delete(taskId);
//        return ResponseEntity.ok("Task deleted successfully!");
//    }


    /**
     * Update an existing task.
     *
     * @param taskUpdatingRequest The request data for updating a task.
     * @return ResponseEntity with a success message upon task update.
     */
//    @PutMapping("/for-admin/update")
//    public ResponseEntity<String> updateTask(@RequestBody TaskUpdatingRequest taskUpdatingRequest) {
//        taskService.update(taskUpdatingRequest);
//        return ResponseEntity.ok("Task updated successfully!");
//    }


    /**
     * Get a task by its ID for a user.
     *
     * @param id The ID of the task to retrieve.
     * @return ResponseEntity with the requested task response.
     */
//    @GetMapping("/for-user/get-task/{id}")
//    public ResponseEntity<TaskResponse> getTaskById(@PathVariable("id") Long id) {
//        return ResponseEntity.ok(taskService.getTaskById(id));
//    }


    /**
     * Get a list of tasks for a user.
     *
     * @param id The ID of the user to retrieve tasks for.
     * @return ResponseEntity with a list of task responses.
     */
//    @GetMapping("/for-user/get-all-task/{id}")
//    public ResponseEntity<List<TaskResponse>> getAllUserTaskList(@PathVariable("id") Long id) {
//        return ResponseEntity.ok(taskService.getAllTaskList(id));
//    }

}