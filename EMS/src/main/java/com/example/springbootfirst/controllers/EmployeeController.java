package com.example.springbootfirst.controllers;

import com.example.springbootfirst.models.Employee;
import com.example.springbootfirst.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") // base path for all employee-related endpoints
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Only accessible to users with ADMIN role
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public String welcome() {
        return "Welcome to SpringBoot Security";
    }

    // Get all employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getMethod();
    }

    // Get employee by ID
    @GetMapping("/employees/{empID}")
    public Employee getEmployeeById(@PathVariable int empID) {
        return employeeService.getEmployeeById(empID);
    }

    // Get employees by job title
    @GetMapping("/employees/job/{job}")
    public List<Employee> getEmployeesByJob(@PathVariable String job) {
        return employeeService.getEmployeeByJob(job);
    }

    // Create a new employee
    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public String addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    // Update an existing employee
    @PutMapping("/employees")
    public String updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    // Delete employee by ID
    @DeleteMapping("/employees/{empID}")
    public String deleteEmployee(@PathVariable int empID) {
        return employeeService.deleteEmployeeById(empID);
    }
}
