package com.example.springintern.services;

import com.example.springintern.models.RegisterDetails;
import com.example.springintern.repository.RegisterDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    RegisterDetailsRepository registerDetailsRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public String addNewEmployee(RegisterDetails register){
        RegisterDetails registerDetails = new RegisterDetails();
        registerDetails.setEmpId(register.getEmpId());
        registerDetails.setEmpName(register.getEmpName());
        registerDetails.setEmail(register.getEmail());
        System.out.println("Password is"+register.getPassword()+"\nEncrypted Password is"+register.getPassword());
        registerDetails.setPassword(passwordEncoder.encode(register.getPassword()));
        registerDetails.setGender(register.getGender());
        registerDetails.setDateOfBirth(register.getDateOfBirth());
        registerDetails.setRole(register.getRole());
        registerDetailsRepository.save(register);

        return "Employee added Successfully";
    }

//    public String authenticate(RegisterDetails login) {
//        RegisterDetails user =  registerDetailsRepository.findByEmail(login.getEmail());
//        if(user!=null){
//            if(passwordEncoder.matches(login.getPassword(),user.getPassword())){
//                return "Successfully logged in";
//            }
//        }
//        return "login not successfully";
//    }
public String authPostLoginService(RegisterDetails login){
    RegisterDetails user=registerDetailsRepository.findByEmail(login.getEmail());
    if(user!=null){
        //if(user.getPassword().equals(login.getPassword())){
        if(passwordEncoder.matches(login.getPassword(), user.getPassword())){
            return "Login Successfully";
        }


    }
    return "Not";
}
}
