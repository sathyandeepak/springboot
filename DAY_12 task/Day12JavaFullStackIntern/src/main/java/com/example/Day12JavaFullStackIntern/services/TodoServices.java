package com.example.Day11JavaFullStackIntern.services;

import com.example.Day11JavaFullStackIntern.models.RegisterDetails;
import com.example.Day11JavaFullStackIntern.models.Todo;
import com.example.Day11JavaFullStackIntern.repository.RegisterDetailsRepository;
import com.example.Day11JavaFullStackIntern.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServices {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    private RegisterDetailsRepository registerDetailRepository;

    public String assignTaskToEmployee(int empID, Todo todo) {
        RegisterDetails employee = registerDetailRepository.findById(empID)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        todo.setEmployee(employee);
        todoRepository.save(todo);

        return "Task assigned to employee with ID: " + empID;
    }

    public List<Todo> getTodoByEmployee(int empID) {
        return todoRepository.findByEmployeeEmpID(empID);
    }


}