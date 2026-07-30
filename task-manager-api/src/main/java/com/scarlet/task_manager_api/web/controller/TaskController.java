package com.scarlet.task_manager_api.web.controller;

import com.scarlet.task_manager_api.web.dto.TaskRequest;
import com.scarlet.task_manager_api.domain.Task;
import com.scarlet.task_manager_api.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(
            summary = "Get all tasks",
            description = "Returns a list of all registered tasks"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks found successfully"),
            @ApiResponse(responseCode = "404", description = "No tasks found")
    })
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {

        List<Task> tasks = taskService.getAllTasks();

        if (tasks.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(tasks);
    }

    @Operation(
            summary = "Get task by ID",
            description = "Returns a task using its identifier"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task found"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(
            @Parameter(description = "Task ID")
            @PathVariable Integer id) {

        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create a task",
            description = "Creates a new task and saves it in the database"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid task data")
    })
    @PostMapping
    public ResponseEntity<Task> createTask(
            @RequestBody TaskRequest request) {


        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setCompleted(request.getCompleted());
        task.setProjectId(request.getProjectId());
        task.setCategoryId(request.getCategoryId());
        task.setUserId(request.getUserId());


        Task savedTask = taskService.createTask(task);


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedTask);
    }

    @Operation(
            summary = "Update a task",
            description = "Updates an existing task"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @Parameter(description = "Task ID")
            @PathVariable Integer id,
            @RequestBody TaskRequest request) {


        try {

            Task task = new Task();

            task.setTitle(request.getTitle());
            task.setDescription(request.getDescription());
            task.setPriority(request.getPriority());
            task.setCompleted(request.getCompleted());
            task.setProjectId(request.getProjectId());
            task.setCategoryId(request.getCategoryId());
            task.setUserId(request.getUserId());


            Task updatedTask = taskService.updateTask(id, task);


            return ResponseEntity.ok(updatedTask);


        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Delete a task",
            description = "Deletes a task by its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task deleted"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
            @Parameter(description = "Task ID")
            @PathVariable Integer id) {

        try {

            taskService.deleteTask(id);

            return ResponseEntity.noContent().build();

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }
}