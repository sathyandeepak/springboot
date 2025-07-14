package com.example.springintern.repository;

import com.example.springintern.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    List<Employee> findByJob(String job);
   // List<Employee> findByFirstName(String firstName);

}
