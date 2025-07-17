package com.example.Day11JavaFullStackIntern.services;

import com.example.Day11JavaFullStackIntern.jwt.JwtTokenProvider;
import com.example.Day11JavaFullStackIntern.models.RegisterDetails;
import com.example.Day11JavaFullStackIntern.models.UserDetailsDto;
import com.example.Day11JavaFullStackIntern.repository.RegisterDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    private RegisterDetailsRepository registerDetailsRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String adUserDetails(UserDetailsDto register) {
        RegisterDetails registerDetails = new RegisterDetails();
        registerDetails.setEmpId(register.getEmpId());
        registerDetails.setName(register.getName());
        registerDetails.setEmail(register.getEmail());
        registerDetails.setPassword(passwordEncoder.encode(register.getPassword()));
        registerDetails.setUserName(register.getUserName());
        Set<Roles> roles = new HashSet<>();
        for (String roleName : register.getRoles()) {
            Roles role = rolesRepository.findByRoleName(roleName).orElseThrow(() -> new RuntimeException("Role not found"));
            System.out.println(role);
            roles.add(role);
        }
        registerDetails.setRoles(roles);
        registerDetailsRepository.save(registerDetails);
        return "User Details saved successfully";
    }

    public String authenticate(RegisterDetails login) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                login.getUserName(), login.getPassword()
        ));
        System.out.println("hello");
        System.out.println(jwtTokenProvider.generateToken(authentication));
        return jwtTokenProvider.generateToken(authentication);
    }

    public Optional<RegisterDetails> getUserByUsername(String username) {
        return Optional.ofNullable(registerDetailsRepository.findByUserName(username));
    }
}