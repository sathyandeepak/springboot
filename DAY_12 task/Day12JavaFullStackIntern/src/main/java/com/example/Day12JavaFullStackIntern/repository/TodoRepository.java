package com.example.Day11JavaFullStackIntern.repository;

import com.example.Day11JavaFullStackIntern.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Integer> {
    List<Todo> findByEmployeeEmpID(int empID);
}