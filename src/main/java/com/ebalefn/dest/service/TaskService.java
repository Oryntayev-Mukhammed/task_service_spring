package com.ebalefn.dest.service;

import com.ebalefn.dest.dto.TaskDTO;
import com.ebalefn.dest.dto.UserDTO;
import com.ebalefn.dest.client.UserClient;
import com.ebalefn.dest.model.Task;
import com.ebalefn.dest.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserClient userClient;

    // Получение всех задач с назначенными пользователями
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(task -> {
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setId(task.getId());
            taskDTO.setTitle(task.getTitle());
            taskDTO.setDescription(task.getDescription());
            taskDTO.setDeadlineDate(task.getDeadlineDate());
            taskDTO.setStatus(task.getStatus());
            taskDTO.setAssignedUserId(task.getAssignedUserId());

            // Получаем пользователя по assignedUserId и заполняем поля
            if (task.getAssignedUserId() != null) {
                UserDTO userDTO = userClient.getUserById(task.getAssignedUserId());
                taskDTO.setAssignedUser(userDTO);  // Устанавливаем объект UserDTO
            }

            return taskDTO;
        }).collect(Collectors.toList());
    }

    // Получение задачи по ID
    public TaskDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setDeadlineDate(task.getDeadlineDate());
        taskDTO.setStatus(task.getStatus());

        // Если assignedUserId не null, заполняем пользователя
        if (task.getAssignedUserId() != null) {
            UserDTO userDTO = userClient.getUserById(task.getAssignedUserId());
            taskDTO.setAssignedUser(userDTO);
            taskDTO.setAssignedUserId(userDTO.getId());
        }

        return taskDTO;
    }

    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setDescription(taskDTO.getDescription());
        task.setAssignedUserId(taskDTO.getAssignedUserId());
        Task savedTask = taskRepository.save(task);
        TaskDTO savedDTO = new TaskDTO();
        savedDTO.setId(savedTask.getId());
        savedDTO.setDescription(savedTask.getDescription());
        savedDTO.setAssignedUserId(savedTask.getAssignedUserId());
        return savedDTO;
    }

    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setDescription(taskDTO.getDescription());
        task.setAssignedUserId(taskDTO.getAssignedUserId());
        Task updatedTask = taskRepository.save(task);
        TaskDTO updatedDTO = new TaskDTO();
        updatedDTO.setId(updatedTask.getId());
        updatedDTO.setDescription(updatedTask.getDescription());
        updatedDTO.setAssignedUserId(updatedTask.getAssignedUserId());
        return updatedDTO;
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
