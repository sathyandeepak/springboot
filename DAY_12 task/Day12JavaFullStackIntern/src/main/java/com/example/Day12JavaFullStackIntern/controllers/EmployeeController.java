package com.example.Day11JavaFullStackIntern.controllers;

import com.example.Day11JavaFullStackIntern.models.RegisterDetails;
import com.example.Day11JavaFullStackIntern.models.UserDetailsDto;
import com.example.Day11JavaFullStackIntern.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/")
    public String  hello(){
        return "Spring security";
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")

    @GetMapping("/employee")
    public List<RegisterDetails> getMethod() {
        return employeeService.getMethod();
    }
    //    @PreAuthorize("hasAnyRole('ADMIN')")
//    @PostMapping("/employee")
//    public String postMethod(@RequestBody UserDetailsDto employee) {
//        return employeeService.addEmployee(employee);
//    }
    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/employee/id/{id}")
    public RegisterDetails getEmployeeById(@PathVariable int id) {

        return employeeService.getEmployeeById(id);
    }
    //    @PreAuthorize("hasAnyRole('ADMIN','USER')")
//    @GetMapping("/employee/job/{job}")
//    public List<RegisterDetails> getEmployeeByJob(@PathVariable String job) {
//
//        return employeeService.getEmployeeByJob(job);
//    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/employee/{id}")
    public String updateEmployee(@PathVariable int id,@RequestBody UserDetailsDto employee) {
        return employeeService.updateEmployee( id,employee);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/employee/{id}")
    public String deleteMethod(@PathVariable int id) {
        return employeeService.deleteMethod(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/employee/roles/{role}")
    public List<RegisterDetails> getbyroles(@PathVariable String role) {
        return employeeService.getbyroles(role);
    }
}