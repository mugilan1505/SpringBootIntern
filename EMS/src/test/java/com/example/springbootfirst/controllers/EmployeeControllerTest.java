package com.example.springbootfirst.controllers;

import com.example.springbootfirst.models.Employee;
import com.example.springbootfirst.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class EmployeeControllerTest {

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRoute() {
        String result = employeeController.route();
        assertEquals("Welcome to SpringBoot Security", result);
    }

    @Test
    void testGetMethod() {
        Employee emp1 = new Employee();
        Employee emp2 = new Employee();

        when(employeeService.getMethod()).thenReturn(Arrays.asList(emp1, emp2));

        List<Employee> result = employeeController.getMethod();

        assertEquals(2, result.size());
    }

    @Test
    void testGetEmployeeById() {
        Employee emp = new Employee();
        emp.setEmpID(1);

       when(employeeService.getEmployeeById(1)).thenReturn(emp);

        Employee result = employeeController.getEmployeeById(1);

        assertEquals(1, result.getEmpID());
    }

    @Test
    void testGetEmployeeByJob() {
        Employee emp1 = new Employee();
        Employee emp2 = new Employee();

        when(employeeService.getEmployeeByJob("developer")).thenReturn(Arrays.asList(emp1, emp2));

        List<Employee> result = employeeController.getEmployeeByJob("developer");

        assertEquals(2, result.size());
    }

    @Test
    void testPostMethod() {
        Employee emp = new Employee();

        when(employeeService.addEmployee(emp)).thenReturn("Employee added successfully");

        String result = employeeController.postMethod(emp);

        assertEquals("Employee added successfully", result);
    }

    @Test
    void testPutMethod() {
        Employee emp = new Employee();

        when(employeeService.updateEmployeeById(1, emp)).thenReturn("Employee updated successfully");

        String result = employeeController.putMethod(1, emp);

        assertEquals("Employee updated successfully", result);
    }

    @Test
    void testDeleteMethod() {
        when(employeeService.deleteEmployeeById(1)).thenReturn("Employee deleted successfully");

        String result = employeeController.deleteMethod(1);

        assertEquals("Employee deleted successfully", result);
    }
}