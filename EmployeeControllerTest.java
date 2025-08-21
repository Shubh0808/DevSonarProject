package com.company.devsonar;

import com.company.devsonar.controller.EmployeeController;
import com.company.devsonar.model.Employee;
import com.company.devsonar.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddEmployee() {
        Employee emp = new Employee(1L, "John", "Doe", 5000.0);
        when(employeeService.addEmployee(emp)).thenReturn(emp);

        Employee result = employeeController.addEmployee(emp);

        assertNotNull(result);
        assertEquals(emp.getId(), result.getId());
        verify(employeeService, times(1)).addEmployee(emp);
    }

    @Test
    void testGetAllEmployees() {
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "John", "Doe", 5000.0),
                new Employee(2L, "Jane", "Smith", 6000.0)
        );

        when(employeeService.getAllEmployees()).thenReturn(employees);

        List<Employee> result = employeeController.getAllEmployees();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    void testGetEmployeeById() {
        Employee emp = new Employee(1L, "John", "Doe", 5000.0);
        when(employeeService.getEmployeeById(1L)).thenReturn(Optional.of(emp));

        assertEquals(emp.getId(), employeeController.getEmployeeById(1L).getBody().getId());
        verify(employeeService, times(1)).getEmployeeById(1L);
    }

    @Test
    void testUpdateEmployee() {
        Employee emp = new Employee(1L, "John", "Doe", 5000.0);
        when(employeeService.updateEmployee(1L, emp)).thenReturn(emp);

        assertEquals(emp.getId(), employeeController.updateEmployee(1L, emp).getBody().getId());
        verify(employeeService, times(1)).updateEmployee(1L, emp);
    }

    @Test
    void testDeleteEmployee() {
        doNothing().when(employeeService).deleteEmployee(1L);

        assertEquals(200, employeeController.deleteEmployee(1L).getStatusCodeValue());
        verify(employeeService, times(1)).deleteEmployee(1L);
    }
}
