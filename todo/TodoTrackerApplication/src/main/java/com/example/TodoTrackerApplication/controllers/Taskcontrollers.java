package com.example.TodoTrackerApplication.controllers;

import com.example.TodoTrackerApplication.models.Task;
import com.example.TodoTrackerApplication.models.Task.Status;
import com.example.TodoTrackerApplication.services.Taskservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class Taskcontrollers {

    @Autowired
    private Taskservice ts;

    
    @GetMapping
    public List<Task> getTasks() {
        return ts.getTaskDetails();
    }

    // Get task by ID
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable int id) {
        return ts.getTaskById(id);
    }


    @PostMapping
    public String addTask(@RequestBody Task task) {
        return ts.postTask(task);
    }


    @PutMapping("/{id}")
    public String updateTask(@PathVariable int id, @RequestBody Task task) {
        return ts.updateTask(id, task);
    }


    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable int id) {
        return ts.deleteTask(id);
    }


    @PutMapping("/{id}/status")
    public String updateTaskStatus(@PathVariable int id, @RequestParam Status status) {
        return ts.updateTaskStatus(id, status);
    }
}
