package com.example.Day11JavaFullStackIntern.services;

import com.example.Day11JavaFullStackIntern.models.RegisterDetails;
import com.example.Day11JavaFullStackIntern.models.UserDetailsDto;
import com.example.Day11JavaFullStackIntern.repository.RegisterDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    @Autowired
    RegisterDetailsRepository registerDetailsRepository;

    @Autowired
    RolesRepository rolesRepository;

    public List<RegisterDetails> getMethod() {
        return registerDetailsRepository.findAll();
    }


//    public String addEmployee(UserDetailsDto employee) {
//        registerDetailsRepository.save(employee);
//        return "Employee added successfully";
//    }

    public RegisterDetails getEmployeeById(int id) {
        return registerDetailsRepository.findById(id).orElse(new RegisterDetails());
    }

    public String updateEmployee(int id,UserDetailsDto employee) {
        Optional<RegisterDetails> existingEmployeeOpt = registerDetailsRepository.findById(id);

        if (existingEmployeeOpt.isPresent()) {
            RegisterDetails existingEmployee = existingEmployeeOpt.get();
            existingEmployee.setName(employee.getName());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setPassword(employee.getPassword());
            existingEmployee.setUserName(employee.getUserName());
            Set<Roles> roles=new HashSet<>();
            for(String roleName:employee.getRoles()){
                Roles role=rolesRepository.findByRoleName(roleName).orElseThrow(()->new RuntimeException("Role not found"));
                System.out.println(role);
                roles.add(role);
            }
            existingEmployee.setRoles(roles);
            registerDetailsRepository.save(existingEmployee);
            return "Employee updated successfully";
        } else {
            throw new RuntimeException("Employee not found with id: " + id);
        }
    }

    public String deleteMethod(int id) {
        registerDetailsRepository.deleteById(id);
        return "Employee deleted successfully";
    }

    public List<RegisterDetails> getbyroles(String role) {
        List<RegisterDetails> employees = new ArrayList<>();

        for(RegisterDetails registerDetails : registerDetailsRepository.findAll()){

            for(Roles roles : registerDetails.getRoles()){
                if(roles.getRoleName().equals(role.toUpperCase())){
                    employees.add(registerDetails);
                }
            }
        }
        return employees;

    }

//    public List<RegisterDetails> getEmployeeByJob(String job) {
//        return registerDetailsRepository.getEmployeeByJob();
//    }
}