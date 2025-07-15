package com.example.TodoTrackerApplication.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private int id;
    private String title;
    private String taskDescription;
    private Status status;

    public enum Status {
        YET_TO_START,
        IN_PROGRESS,
        COMPLETED
    }
}
