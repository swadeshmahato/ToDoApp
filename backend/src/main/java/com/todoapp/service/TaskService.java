package com.todoapp.service;

import com.todoapp.model.Task;
import com.todoapp.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Create a new task
    public Task createTask(
            String title,
            LocalDate dueDate,
            Task.Priority priority,
            String category) {

        Task task = new Task(
                null,
                title,
                false,
                dueDate,
                priority,
                category
        );

        return taskRepository.save(task);
    }

    // Get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Get task by ID
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // Update task
    public Optional<Task> updateTask(
            Long id,
            String title,
            Boolean completed,
            LocalDate dueDate,
            Task.Priority priority,
            String category) {

        return taskRepository.findById(id).map(task -> {

            if (title != null) {
                task.setTitle(title);
            }

            if (completed != null) {
                task.setCompleted(completed);
            }

            if (dueDate != null) {
                task.setDueDate(dueDate);
            }

            if (priority != null) {
                task.setPriority(priority);
            }

            if (category != null) {
                task.setCategory(category);
            }

            return taskRepository.save(task);
        });
    }

    // Delete task
    public boolean deleteTask(Long id) {

        if (!taskRepository.existsById(id)) {
            return false;
        }

        taskRepository.deleteById(id);
        return true;
    }
}
