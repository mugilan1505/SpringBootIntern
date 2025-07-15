package com.example.springbootfirst.services;

import com.example.springbootfirst.models.Employee;
import com.example.springbootfirst.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository empRepo;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMethod() {
        Employee emp1 = new Employee(1, "Alice", "Developer");
        Employee emp2 = new Employee(2, "Bob", "Manager");

        when(empRepo.findAll()).thenReturn(Arrays.asList(emp1, emp2));

        List<Employee> result = employeeService.getMethod();

        assertEquals(2, result.size());
        verify(empRepo).findAll();
    }

    @Test
    void testGetEmployeeById() {
        Employee emp = new Employee(1, "Alice", "Developer");
        when(empRepo.findByEmpID(1)).thenReturn(emp);

        Employee result = employeeService.getEmployeeById(1);

        assertEquals(emp, result);
        verify(empRepo).findByEmpID(1);
    }

    @Test
    void testGetEmployeeByJob() {
        Employee emp1 = new Employee(1, "Alice", "Developer");
        Employee emp2 = new Employee(3, "Charlie", "Developer");

        when(empRepo.findByJob("Developer")).thenReturn(Arrays.asList(emp1, emp2));

        List<Employee> result = employeeService.getEmployeeByJob("Developer");

        assertEquals(2, result.size());
        verify(empRepo).findByJob("Developer");
    }

    @Test
    void testAddEmployee() {
        Employee emp = new Employee(0, "Dave", "Tester");

        // no return from save, just verify it's called
        doNothing().when(empRepo).save(emp);

        String result = employeeService.addEmployee(emp);

        assertEquals("Employee Added Successfully!!!", result);
        verify(empRepo).save(emp);
    }

    @Test
    void testUpdateEmployeeById() {
        Employee existingEmp = new Employee(1, "Alice", "Developer");
        Employee updatedEmp = new Employee(1, "Alice Updated", "Senior Developer");

        when(empRepo.findById(1)).thenReturn(Optional.of(existingEmp));
        when(empRepo.save(existingEmp)).thenReturn(existingEmp);

        String result = employeeService.updateEmployeeById(1, updatedEmp);

        assertEquals("Employee with ID 1 updated successfully.", result);
        assertEquals("Alice Updated", existingEmp.getName());
        assertEquals("Senior Developer", existingEmp.getJob());
        verify(empRepo).findById(1);
        verify(empRepo).save(existingEmp);
    }

    @Test
    void testDeleteEmployeeById() {
        doNothing().when(empRepo).deleteById(1);

        String result = employeeService.deleteEmployeeById(1);

        assertEquals("Employee Deleted Successfully!!!", result);
        verify(empRepo).deleteById(1);
    }
}