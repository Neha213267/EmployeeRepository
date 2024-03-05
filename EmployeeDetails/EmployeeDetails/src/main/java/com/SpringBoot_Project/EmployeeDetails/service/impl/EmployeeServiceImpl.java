package com.SpringBoot_Project.EmployeeDetails.service.impl;

import com.SpringBoot_Project.EmployeeDetails.entity.Employee;
import com.SpringBoot_Project.EmployeeDetails.exception.EmailAlreadyExistsException;
import com.SpringBoot_Project.EmployeeDetails.exception.EmployeeNotFoundException;
import com.SpringBoot_Project.EmployeeDetails.repository.IEmployeeRepository;
import com.SpringBoot_Project.EmployeeDetails.service.IEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {

    private static final Logger logger = Logger.getLogger(EmployeeServiceImpl.class.getName());

    @Autowired
    private IEmployeeRepository iEmployeeRepository;

    @Override
    public Employee createEmployee(Employee employee){
        Optional<Employee> optionalUser = iEmployeeRepository.findByEmail(employee.getEmail());
        if(optionalUser.isPresent()){
            logger.log(Level.SEVERE, "Email Already Exists for Employee ");
            throw new EmailAlreadyExistsException("Email Already Exists for Employee ");
        }
        logger.log(Level.INFO, "Employee created");
        return iEmployeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees(){
        logger.log(Level.INFO, "Getting all employees");
        return iEmployeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long empId) {
        logger.log(Level.INFO, "Getting employee by id: " + empId);
        return iEmployeeRepository.findById(empId)
                .orElseThrow(() -> {
                    logger.log(Level.SEVERE, "Employee not found with id: " + empId);
                    return new EmployeeNotFoundException("Employee not found with id: " + empId);
                });

    }

    @Override
    public Employee updateEmployee(Long empId, Employee employee) {
        if (!iEmployeeRepository.existsById(empId)) {
            logger.log(Level.SEVERE, "Employee not found with id: " + empId);
            throw new EmployeeNotFoundException("Employee not found with id: " + empId);
        }
        employee.setId(empId);
        logger.log(Level.INFO, "Updated employee by id: " + empId);
        return iEmployeeRepository.save(employee);
    }


    @Override
    public void deleteEmployee(Long empId) {
        Employee employee = iEmployeeRepository.findById(empId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + empId));
        iEmployeeRepository.delete(employee);
    }
}
