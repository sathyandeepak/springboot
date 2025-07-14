package com.example.springbootfirst.services;

import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.models.Roles;
import com.example.springbootfirst.models.UserDetailsDto;
import com.example.springbootfirst.repository.RegisterDetailsRepository;
import com.example.springbootfirst.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {

    @Autowired
    RegisterDetailsRepository regRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepo;

    public List<RegisterDetails> getAllEmployees(){
        return regRepo.findAll();
    }

    public RegisterDetails getEmployeeById(int id){
        return regRepo.findById(id).get();
    }

    public String addEmployee(UserDetailsDto register) {
        RegisterDetails registerDetails = new RegisterDetails();
        registerDetails.setEmpId(register.getEmpId());
        registerDetails.setName(register.getName());
        registerDetails.setEmail(register.getEmail());
        registerDetails.setPassword(passwordEncoder.encode(register.getPassword()));
        registerDetails.setUserName(register.getUserName());

        Set<Roles> roles = new HashSet<>();
        for (String roleName : register.getRoleNames()) {
            Roles role = roleRepo.findByRoleName(roleName)
                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
            roles.add(role);
        }
        registerDetails.setRoles(roles);

        regRepo.save(registerDetails);
        return "User registered successfully!";
    }

    public String updateEmployeeById(int id, RegisterDetails updatedData) {
        RegisterDetails existing = regRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        existing.setName(updatedData.getName());
        existing.setUserName(updatedData.getUserName());
        existing.setEmail(updatedData.getEmail());
        // avoid changing empId or password here unless intentional

        regRepo.save(existing);
        return "Employee updated successfully";
    }


    public String deleteEmployeeById(int id) {
        if (!regRepo.existsById(id)) {
            throw new RuntimeException("Employee not found with id: " + id);
        }
        regRepo.deleteById(id);
        return "Employee deleted successfully";
    }


    public String deleteAllEmployees(){
        regRepo.deleteAll();
        return "All employee deleted successfully";
    }
}
