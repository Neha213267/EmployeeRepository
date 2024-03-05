package com.SpringBoot_Project.EmployeeDetails.controller;

import com.SpringBoot_Project.EmployeeDetails.entity.Employee;
import com.SpringBoot_Project.EmployeeDetails.service.IEmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for Employee Resource",
        description = "CRUD REST APIs - Create Employee, Get All Employees,Get Employee, Update Employee, Delete Employee"
)
@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeController {

    @Autowired
    private IEmployeeService iEmployeeService;

    @Operation(
            summary = "Create Employee REST API",
            description = "Create Employee REST API is used to save employee in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    //build create Employee REST API
    // http://localhost:8080/api/employee/createEmp
    @PostMapping("/createEmp")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        try {
            Employee createdEmployee = iEmployeeService.createEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Operation(
            summary = "Get All Employees REST API",
            description = "Get All Employees REST API is used to get a all the employees from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    //build Get All Employee REST API
    // http://localhost:8080/api/employee/getAllEmp
    @GetMapping("/getAllEmp")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            List<Employee> employees = iEmployeeService.getAllEmployees();
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(
            summary = "Get Employee By ID REST API",
            description = "Get Employee By ID REST API is used to get a single Employee from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    // build get employee by id REST API
    // http://localhost:8080/api/employee/1
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = iEmployeeService.getEmployeeById(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(
            summary = "Update Employee REST API",
            description = "Update Employee REST API is used to update a particular employee in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    // Build Update Employee REST API
    // http://localhost:8080/api/employee/1
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        try {
            Employee updatedEmployee = iEmployeeService.updateEmployee(id, employee);
            return ResponseEntity.ok(updatedEmployee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Operation(
            summary = "Delete Employee REST API",
            description = "Delete Employee REST API is used to delete a particular employee from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    // Build Delete Employee REST API
    // http://localhost:8080/api/employee/delete/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        try {
            iEmployeeService.deleteEmployee(id);
            return ResponseEntity.ok("Employee with ID " + id + " deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete employee with ID " + id);
        }

    }
}