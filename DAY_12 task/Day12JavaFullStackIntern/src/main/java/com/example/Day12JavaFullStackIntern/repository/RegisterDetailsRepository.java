package com.example.Day11JavaFullStackIntern.repository;

import com.example.Day11JavaFullStackIntern.models.RegisterDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RegisterDetailsRepository extends JpaRepository<RegisterDetails,Integer> {

    RegisterDetails findByEmail(String email);


    RegisterDetails findByUserName(String userName);
}