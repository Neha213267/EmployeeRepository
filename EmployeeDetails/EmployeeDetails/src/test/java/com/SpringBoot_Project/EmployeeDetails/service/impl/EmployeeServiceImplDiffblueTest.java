package com.SpringBoot_Project.EmployeeDetails.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.SpringBoot_Project.EmployeeDetails.entity.Employee;
import com.SpringBoot_Project.EmployeeDetails.exception.EmailAlreadyExistsException;
import com.SpringBoot_Project.EmployeeDetails.exception.EmployeeNotFoundException;
import com.SpringBoot_Project.EmployeeDetails.repository.IEmployeeRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmployeeServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class EmployeeServiceImplDiffblueTest {
  @Autowired
  private EmployeeServiceImpl employeeServiceImpl;

  @MockBean
  private IEmployeeRepository iEmployeeRepository;

  /**
   * Method under test: {@link EmployeeServiceImpl#createEmployee(Employee)}
   */
  @Test
  void testCreateEmployee() {
    // Arrange
    Employee employee = new Employee();
    employee.setAddress("42 Main St");
    employee.setEmail("jane.doe@example.org");
    employee.setFirstName("Jane");
    employee.setId(1L);
    employee.setLastName("Doe");
    Optional<Employee> ofResult = Optional.of(employee);
    when(iEmployeeRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);

    Employee employee2 = new Employee();
    employee2.setAddress("42 Main St");
    employee2.setEmail("jane.doe@example.org");
    employee2.setFirstName("Jane");
    employee2.setId(1L);
    employee2.setLastName("Doe");

    // Act and Assert
    assertThrows(EmailAlreadyExistsException.class, () -> employeeServiceImpl.createEmployee(employee2));
    verify(iEmployeeRepository).findByEmail(eq("jane.doe@example.org"));
  }

  /**
   * Method under test: {@link EmployeeServiceImpl#createEmployee(Employee)}
   */
  @Test
  void testCreateEmployee2() {
    // Arrange
    Employee employee = new Employee();
    employee.setAddress("42 Main St");
    employee.setEmail("jane.doe@example.org");
    employee.setFirstName("Jane");
    employee.setId(1L);
    employee.setLastName("Doe");
    when(iEmployeeRepository.save(Mockito.<Employee>any())).thenReturn(employee);
    Optional<Employee> emptyResult = Optional.empty();
    when(iEmployeeRepository.findByEmail(Mockito.<String>any())).thenReturn(emptyResult);

    Employee employee2 = new Employee();
    employee2.setAddress("42 Main St");
    employee2.setEmail("jane.doe@example.org");
    employee2.setFirstName("Jane");
    employee2.setId(1L);
    employee2.setLastName("Doe");

    // Act
    Employee actualCreateEmployeeResult = employeeServiceImpl.createEmployee(employee2);

    // Assert
    verify(iEmployeeRepository).findByEmail(eq("jane.doe@example.org"));
    verify(iEmployeeRepository).save(Mockito.<Employee>any());
    assertSame(employee, actualCreateEmployeeResult);
  }

  /**
   * Method under test: {@link EmployeeServiceImpl#createEmployee(Employee)}
   */
  @Test
  void testCreateEmployee3() {
    // Arrange
    when(iEmployeeRepository.findByEmail(Mockito.<String>any()))
        .thenThrow(new EmployeeNotFoundException("An error occurred"));

    Employee employee = new Employee();
    employee.setAddress("42 Main St");
    employee.setEmail("jane.doe@example.org");
    employee.setFirstName("Jane");
    employee.setId(1L);
    employee.setLastName("Doe");

    // Act and Assert
    assertThrows(EmployeeNotFoundException.class, () -> employeeServiceImpl.createEmployee(employee));
    verify(iEmployeeRepository).findByEmail(eq("jane.doe@example.org"));
  }

  /**
   * Method under test: {@link EmployeeServiceImpl#getAllEmployees()}
   */
  @Test
  void testGetAllEmployees() {
    // Arrange
    ArrayList<Employee> employeeList = new ArrayList<>();
    when(iEmployeeRepository.findAll()).thenReturn(employeeList);

    // Act
    List<Employee> actualAllEmployees = employeeServiceImpl.getAllEmployees();

    // Assert
    verify(iEmployeeRepository).findAll();
    assertTrue(actualAllEmployees.isEmpty());
    assertSame(employeeList, actualAllEmployees);
  }

  /**
   * Method under test: {@link EmployeeServiceImpl#getAllEmployees()}
   */
  @Test
  void testGetAllEmployees2() {
    // Arrange
    when(iEmployeeRepository.findAll()).thenThrow(new EmailAlreadyExistsException("An error occurred"));

    // Act and Assert
    assertThrows(EmailAlreadyExistsException.class, () -> employeeServiceImpl.getAllEmployees());
    verify(iEmployeeRepository).findAll();
  }

  /**
   * Method under test: {@link EmployeeServiceImpl#getEmployeeById(Long)}
   */
  @Test
  void testGetEmployeeById() {
    // Arrange
    Employee employee = new Employee();
    employee.setAddress("42 Main St");
    employee.setEmail("jane.doe@example.org");
    employee.setFirstName("Jane");
    employee.setId(1L);
    employee.setLastName("Doe");
    Optional<Employee> ofResult = Optional.of(employee);
    when(iEmployeeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act
    Employee actualEmployeeById = employeeServiceImpl.getEmployeeById(1L);

    // Assert
    verify(iEmployeeRepository).findById(Mockito.<Long>any());
    assertSame(employee, actualEmployeeById);
  }

  /**
   * Method under test: {@link EmployeeServiceImpl#getEmployeeById(Long)}
   */
  @Test
  void testGetEmployeeById2() {
    // Arrange
    Optional<Employee> emptyResult = Optional.empty();
    when(iEmployeeRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

    // Act and Assert
    assertThrows(EmployeeNotFoundException.class, () -> employeeServiceImpl.getEmployeeById(1L));
    verify(iEmployeeRepository).findById(Mockito.<Long>any());
  }

  /**
   * Method under test: {@link EmployeeServiceImpl#getEmployeeById(Long)}
   */
  @Test
  void testGetEmployeeById3() {
    // Arrange
    when(iEmployeeRepository.findById(Mockito.<Long>any()))
        .thenThrow(new EmailAlreadyExistsException("An error occurred"));

    // Act and Assert
    assertThrows(EmailAlreadyExistsException.class, () -> employeeServiceImpl.getEmployeeById(1L));
    verify(iEmployeeRepository).findById(Mockito.<Long>any());
  }

  /**
   * Method under test: {@link EmployeeServiceImpl#updateEmployee(Long, Employee)}
   */
  @Test
  void testUpdateEmployee() {
    // Arrange
    Employee employee = new Employee();
    employee.setAddress("42 Main St");
    employee.setEmail("jane.doe@example.org");
    employee.setFirstName("Jane");
    employee.setId(1L);
    employee.setLastName("Doe");
    when(iEmployeeRepository.save(Mockito.<Employee>any())).thenReturn(employee);
    when(iEmployeeRepository.existsById(Mockito.<Long>any())).thenReturn(true);

    Employee employee2 = new Employee();
    employee2.setAddress("42 Main St");
    employee2.setEmail("jane.doe@example.org");
    employee2.setFirstName("Jane");
    employee2.setId(1L);
    employee2.setLastName("Doe");

    // Act
    Employee actualUpdateEmployeeResult = employeeServiceImpl.updateEmployee(1L, employee2);

    // Assert
    verify(iEmployeeRepository).existsById(Mockito.<Long>any());
    verify(iEmployeeRepository).save(Mockito.<Employee>any());
    assertEquals(1L, employee2.getId().longValue());
    assertSame(employee, actualUpdateEmployeeResult);
  }

  /**
   * Method under test: {@link EmployeeServiceImpl#updateEmployee(Long, Employee)}
   */
  @Test
  void testUpdateEmployee2() {
    // Arrange
    when(iEmployeeRepository.save(Mockito.<Employee>any()))
        .thenThrow(new EmailAlreadyExistsException("An error occurred"));
    when(iEmployeeRepository.existsById(Mockito.<Long>any())).thenReturn(true);

    Employee employee = new Employee();
    employee.setAddress("42 Main St");
    employee.setEmail("jane.doe@example.org");
    employee.setFirstName("Jane");
    employee.setId(1L);
    employee.setLastName("Doe");

    // Act and Assert
    assertThrows(EmailAlreadyExistsException.class, () -> employeeServiceImpl.updateEmployee(1L, employee));
    verify(iEmployeeRepository).existsById(Mockito.<Long>any());
    verify(iEmployeeRepository).save(Mockito.<Employee>any());
  }

  /**
   * Method under test: {@link EmployeeServiceImpl#updateEmployee(Long, Employee)}
   */
  @Test
  void testUpdateEmployee3() {
    // Arrange
    when(iEmployeeRepository.existsById(Mockito.<Long>any())).thenReturn(false);

    Employee employee = new Employee();
    employee.setAddress("42 Main St");
    employee.setEmail("jane.doe@example.org");
    employee.setFirstName("Jane");
    employee.setId(1L);
    employee.setLastName("Doe");

    // Act and Assert
    assertThrows(EmployeeNotFoundException.class, () -> employeeServiceImpl.updateEmployee(1L, employee));
    verify(iEmployeeRepository).existsById(Mockito.<Long>any());
  }

  /**
   * Method under test: {@link EmployeeServiceImpl#deleteEmployee(Long)}
   */
  @Test
  void testDeleteEmployee() {
    // Arrange
    Employee employee = new Employee();
    employee.setAddress("42 Main St");
    employee.setEmail("jane.doe@example.org");
    employee.setFirstName("Jane");
    employee.setId(1L);
    employee.setLastName("Doe");
    Optional<Employee> ofResult = Optional.of(employee);
    doNothing().when(iEmployeeRepository).delete(Mockito.<Employee>any());
    when(iEmployeeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act
    employeeServiceImpl.deleteEmployee(1L);

    // Assert that nothing has changed
    verify(iEmployeeRepository).delete(Mockito.<Employee>any());
    verify(iEmployeeRepository).findById(Mockito.<Long>any());
    assertTrue(employeeServiceImpl.getAllEmployees().isEmpty());
  }

  /**
   * Method under test: {@link EmployeeServiceImpl#deleteEmployee(Long)}
   */
  @Test
  void testDeleteEmployee2() {
    // Arrange
    Employee employee = new Employee();
    employee.setAddress("42 Main St");
    employee.setEmail("jane.doe@example.org");
    employee.setFirstName("Jane");
    employee.setId(1L);
    employee.setLastName("Doe");
    Optional<Employee> ofResult = Optional.of(employee);
    doThrow(new EmailAlreadyExistsException("An error occurred")).when(iEmployeeRepository)
        .delete(Mockito.<Employee>any());
    when(iEmployeeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act and Assert
    assertThrows(EmailAlreadyExistsException.class, () -> employeeServiceImpl.deleteEmployee(1L));
    verify(iEmployeeRepository).delete(Mockito.<Employee>any());
    verify(iEmployeeRepository).findById(Mockito.<Long>any());
  }
}
