package com.example.Day11JavaFullStackIntern.controllers;

import com.example.Day11JavaFullStackIntern.models.RegisterDetails;
import com.example.Day11JavaFullStackIntern.models.UserDetailsDto;
import com.example.Day11JavaFullStackIntern.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping
    public String addUserDetails(@RequestBody UserDetailsDto userDetails) {
        return authService.adUserDetails(userDetails);
    }
    @PostMapping("/login")
    public String login(@RequestBody RegisterDetails userDetails) {
        return authService.authenticate(userDetails);
    }
}