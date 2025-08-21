package com.company.devsonar.service;

import com.company.devsonar.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final List<Employee> employees = new ArrayList<>();

    // Add Employee
    public Employee addEmployee(Employee employee) {
        employees.add(employee);
        return employee;
    }

    // Get all Employees
    public List<Employee> getAllEmployees() {
        return employees;
    }

    // Get Employee by ID
    public Optional<Employee> getEmployeeById(Long id) {
        return employees.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    // Update Employee
    public Employee updateEmployee(Long id, Employee updated) {
        Optional<Employee> empOpt = getEmployeeById(id);
        if(empOpt.isPresent()) {
            Employee emp = empOpt.get();
            emp.setName(updated.getName());
            emp.setDepartment(updated.getDepartment());
            emp.setSalary(updated.getSalary());
            return emp;
        }
        return null;
    }

    // Delete Employee
    public boolean deleteEmployee(Long id) {
        return employees.removeIf(e -> e.getId().equals(id));
    }
}
