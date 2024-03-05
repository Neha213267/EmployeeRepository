package com.SpringBoot_Project.EmployeeDetails.service;

import com.SpringBoot_Project.EmployeeDetails.entity.Employee;
import com.SpringBoot_Project.EmployeeDetails.exception.EmployeeNotFoundException;

import java.util.List;

public interface IEmployeeService {

     Employee createEmployee(Employee employee);

     List<Employee> getAllEmployees();

     Employee getEmployeeById(Long id) ;

    Employee updateEmployee(Long empId, Employee employee);

     void deleteEmployee(Long empId);
    }
