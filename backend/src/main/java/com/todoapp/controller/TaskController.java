package com.todoapp.controller;

import com.todoapp.model.Task;
import com.todoapp.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Get all tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // Get one task
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {

        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create task
    @PostMapping
    public Task createTask(@RequestBody TaskRequest request) {

        return taskService.createTask(
                request.title(),
                request.dueDate(),
                request.priority(),
                request.category()
        );
    }

    // Update task
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long id,
            @RequestBody TaskRequest request) {

        return taskService.updateTask(
                id,
                request.title(),
                request.completed(),
                request.dueDate(),
                request.priority(),
                request.category()
        )
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    // Delete task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {

        return taskService.deleteTask(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    // Request data
    public record TaskRequest(
            String title,
            Boolean completed,
            LocalDate dueDate,
            Task.Priority priority,
            String category
    ) {
    }
}