package com.SpringBoot_Project.EmployeeDetails.repository;

import com.SpringBoot_Project.EmployeeDetails.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee,Long> {

    Optional<Employee> findByEmail(String email);
}
