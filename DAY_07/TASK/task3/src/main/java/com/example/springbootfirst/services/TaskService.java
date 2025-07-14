package com.example.springbootfirst.services;

import com.example.springbootfirst.models.Task;
import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.repository.TaskRepository;
import com.example.springbootfirst.repository.RegisterDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepo;

    @Autowired
    private RegisterDetailsRepository registerRepo;


    public String createTask(Task task) {
        // Check if employee exists
        RegisterDetails employee = registerRepo.findById(task.getEmpId())
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + task.getEmpId()));

        taskRepo.save(task);
        return "Task added successfully";
    }


    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }


    public Task getTaskById(int id) {
        return taskRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + id));
    }


    public List<Task> getTasksByEmpId(int empId) {
        return taskRepo.findByEmpId(empId);
    }


    public String updateTask(int id, Task updatedTask) {
        Task existingTask = taskRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + id));

        registerRepo.findById(updatedTask.getEmpId())
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + updatedTask.getEmpId()));

        existingTask.setTaskName(updatedTask.getTaskName());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStatus(updatedTask.getStatus());
        existingTask.setEmpId(updatedTask.getEmpId());

        taskRepo.save(existingTask);
        return "Task updated successfully";
    }


    public String deleteTask(int id) {
        taskRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + id));
        taskRepo.deleteById(id);
        return "Task deleted successfully";
    }
}
