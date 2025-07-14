package com.example.springbootfirst.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        /*
        3 things
        1. Loading data from your database
        2. setting up the authorities
        3. returning up proper UserDetails
         */

        return null;
    }
}
