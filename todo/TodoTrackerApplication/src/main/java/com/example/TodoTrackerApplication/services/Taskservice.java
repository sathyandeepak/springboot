package com.example.TodoTrackerApplication.services;

import com.example.TodoTrackerApplication.models.Task;
import com.example.TodoTrackerApplication.models.Task.Status;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class Taskservice {


    List<Task> ls = new ArrayList<>(
            Arrays.asList(
                    new Task(1, "Build Backend", "Create APIs using Spring Boot", Status.YET_TO_START),
                    new Task(2, "Design UI", "Design user interface with HTML/CSS", Status.IN_PROGRESS),
                    new Task(3, "Write Tests", "Write unit tests using JUnit", Status.COMPLETED)
            )
    );



    public List<Task> getTaskDetails() {
        return ls;
    }


    public Task getTaskById(int id) {
        for (Task t : ls) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }


    public String postTask(Task task) {
        ls.add(task);
        return "Task added successfully";
    }


    public String updateTask(int id, Task updatedTask) {
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).getId() == id) {
                ls.set(i, updatedTask);
                return "Updated successfully";
            }
        }
        return "Task not found";
    }


    public String deleteTask(int id) {
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).getId() == id) {
                ls.remove(i);
                return "Deleted successfully";
            }
        }
        return "Task not found";
    }

    public String updateTaskStatus(int id, Status newStatus) {
        for (Task task : ls) {
            if (task.getId() == id) {
                task.setStatus(newStatus);
                return "Status updated to: " + newStatus;
            }
        }
        return "Task not found";
    }
}
