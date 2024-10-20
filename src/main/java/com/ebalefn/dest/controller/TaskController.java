package com.ebalefn.dest.controller;

import com.ebalefn.dest.model.Task; // Это правильный импорт
import com.ebalefn.dest.client.UserClient;
import com.ebalefn.dest.dto.UserDTO;
import com.ebalefn.dest.dto.TaskDTO;
import com.ebalefn.dest.service.TaskService;
import com.ebalefn.dest.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private TaskService taskService;


    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<TaskDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskDTO getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(updatedTask.getTitle());
                    task.setDescription(updatedTask.getDescription());
                    task.setDeadlineDate(updatedTask.getDeadlineDate());
                    task.setStatus(updatedTask.getStatus());
                    return taskRepository.save(task);
                })
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        try {
            return userClient.getAllUsers();
        } catch (Exception e) {
            // Логируйте ошибку и возвращайте более информативное сообщение
            System.err.println("Ошибка при вызове User Service: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Не удалось получить пользователей");
        }
    }


    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userClient.getUserById(id);
    }

    @GetMapping("/kola")
    public String Kola() {
        return "Soda";
    }

}
