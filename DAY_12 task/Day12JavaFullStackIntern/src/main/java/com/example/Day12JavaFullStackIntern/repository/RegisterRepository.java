package com.example.Day11JavaFullStackIntern.repository;

import com.example.Day11JavaFullStackIntern.models.RegisterDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisterRepository extends JpaRepository<RegisterDetails,Integer> {

    Optional<RegisterDetails> findByUserName(String username);
}