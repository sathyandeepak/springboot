package com.example.Day11JavaFullStackIntern.services;

import com.example.Day11JavaFullStackIntern.models.RegisterDetails;
import com.example.Day11JavaFullStackIntern.repository.RegisterDetailsRepository;
import com.example.Day11JavaFullStackIntern.repository.RegisterDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    RegisterDetailsRepository registerDetailsRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /*
            3 things

            1. Loading data from database
            2. Setting up the authorities (roles/permissions)
            3. Returning proper UserDetails
        */

        // Step 1: Load user from database using the repository
        RegisterDetails user = registerDetailsRepo.findByUserName(username);

        // Step 2: Convert user roles to Spring Security authorities
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(roles -> new SimpleGrantedAuthority(roles.getRoleName()))
                .collect(Collectors.toSet());

        System.out.println("username is" + user.getUserName() + "\npassword is " + user.getPassword() + "\nAu" + authorities);

        // Step 3: Return Spring Security User object with username, password, and authorities
        return new User(user.getUserName(), user.getPassword(), authorities);
    }
}