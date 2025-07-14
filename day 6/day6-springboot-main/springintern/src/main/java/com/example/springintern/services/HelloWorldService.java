package com.example.springintern.services;

import com.example.springintern.models.Employee;
import com.example.springintern.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelloWorldService {
//    List<Employee> emp = new ArrayList<>(
//            Arrays.asList(new Employee(1, "Arjun", "Prasath", "Arjun@gmail.com", "1234", "Trainer"),
//                    new Employee(2, "Ed", "Sheeran", "shreen@gmail.com", "5678", "Singer")
//            )
//    );

    @Autowired
    EmployeeRepository empRepo;

    public List<Employee> getMethod() {
        return empRepo.findAll();
    }

    public Employee getEmployeeById(int empId) {
        return empRepo.findById(empId).orElse(new Employee());

    }
    public List<Employee> getEmployeeByJob(String job) {
        return empRepo.findByJob(job);
    }
//    public List<Employee> getEmployeeByFirstName(String firstName) {
//        return empRepo.findByFirstName(firstName);
//    }
    public String  addEmployee(Employee emp) {
        empRepo.save(emp);
        return "Employee added successfully";
    }
    public String updateEmployee(Employee emp){
        empRepo.save(emp);
        return "Employee updated successfully";
    }
    public String deleteEmployee(int empId) {
        empRepo.deleteById(empId);
        return "Employee deleted successfully";
    }
    public String deleteAllEmployees(){
        empRepo.deleteAll();
        return "All Employees Deleted Successfully";
    }

}
