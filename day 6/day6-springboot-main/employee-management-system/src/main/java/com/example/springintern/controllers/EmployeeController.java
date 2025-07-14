package com.example.springintern.controllers;

import com.example.springintern.models.Employee;
import com.example.springintern.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService hws;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/")
    public String router(){
        return "Welcome to Spring Boot security!";
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/employee")
    public List<Employee> hello(){
        return hws.getMethod();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/employee/{empId}")
    public Employee getMethodById(@PathVariable int empId){
        return hws.getEmployeeById(empId);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/job/{job}")
    public List<Employee> getEmployeeByJob(@PathVariable String job){
        return hws.getEmployeeByJob(job);
    }

//    @GetMapping("/firstName/{firstName}")
//    public List<Employee> getEmployeeByFirstName(@PathVariable String firstName){
//        return hws.getEmployeeByFirstName(firstName);
//    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public String postMethod(@RequestBody Employee emp){
        return hws.addEmployee(emp);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{empId}")
    public String putMethod(@RequestBody Employee emp){
        return hws.updateEmployee(emp);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{empId}")
    public String deleteMethod(@PathVariable int empId){
        return hws.deleteEmployee(empId);
    }
//    @DeleteMapping("/")
//    public String deleteAllMethod(){
//        return hws.deleteAllEmployees();
//    }

}