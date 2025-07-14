package com.example.springbootfirst.controllers;

import com.example.springbootfirst.models.Task;
import com.example.springbootfirst.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;


    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }


    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable int id) {
        return taskService.getTaskById(id);
    }


    @GetMapping("/employee/{empId}")
    public List<Task> getTasksByEmployeeId(@PathVariable int empId) {
        return taskService.getTasksByEmpId(empId);
    }


    @PostMapping
    public String createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }


    @PutMapping("/update/{id}")
    public String updateTask(@PathVariable int id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }


    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable int id) {
        return taskService.deleteTask(id);
    }
}
