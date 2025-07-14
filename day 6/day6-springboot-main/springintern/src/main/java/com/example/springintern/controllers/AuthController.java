package com.example.springintern.controllers;

import com.example.springintern.models.RegisterDetails;
import com.example.springintern.repository.RegisterDetailsRepository;
import com.example.springintern.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;
    @Autowired
    private RegisterDetailsRepository registerDetailsRepository;

    @PostMapping("/register")
    public String addNewUser(@RequestBody RegisterDetails register){
        return  authService.addNewEmployee(register);
    }

    @PostMapping("/login")
    public String login(@RequestBody RegisterDetails login){
        RegisterDetails user = registerDetailsRepository.findByEmail(login.getEmail());
        if(user!=null){
            if(user.getPassword().equals(login.getPassword())){
                return "Login successful";
            }
        }
        return "Login not successful";
    }
}
