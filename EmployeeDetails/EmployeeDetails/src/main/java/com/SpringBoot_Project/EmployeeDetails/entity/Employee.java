package com.SpringBoot_Project.EmployeeDetails.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Employee First Name should not be Empty
    @Column(name = "firstName", nullable = false)
    @NotEmpty(message = "Employee firstName should not be null or empty")
    private String firstName;

    //Employee Last Name should not be Empty
    @Column(name = "lastName", nullable = false)
    @NotEmpty(message = "Employee lastName should not be null or empty")
    private String lastName;

    //Employee Email Name should not be Empty
    //Email address should be valid
    @Column(name = "email", nullable = false)
    @NotEmpty(message = "Employee email should not be null or empty")
    @Email(message = "Email address should be valid")
    private String email;

    //Employee Address should not be Empty
    @Column(name = "address", nullable = false)
    @NotEmpty(message = "Employee address should not be null or empty")
    private String address;


}

