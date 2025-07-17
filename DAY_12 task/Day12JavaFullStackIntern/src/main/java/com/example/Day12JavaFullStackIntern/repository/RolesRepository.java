package com.example.Day11JavaFullStackIntern.repository;

import com.example.Day11JavaFullStackIntern.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles,Integer> {
    Optional<Roles>findByRoleName(String roleName);
}