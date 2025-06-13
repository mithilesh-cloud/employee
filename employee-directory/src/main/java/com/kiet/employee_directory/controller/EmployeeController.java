package com.kiet.employee_directory.controller;

import com.kiet.employee_directory.model.Employee;
import com.kiet.employee_directory.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // GET all employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // GET employee by id
    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // GET employees by department
    @GetMapping("/employees/department/{dept}")
    public List<Employee> getEmployeesByDepartment(@PathVariable String dept) {
        return employeeRepository.findByDepartment(dept);
    }

    // POST - create employee
    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // PUT - update employee
    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setName(updatedEmployee.getName());
            employee.setEmail(updatedEmployee.getEmail());
            employee.setDepartment(updatedEmployee.getDepartment());
            employee.setDesignation(updatedEmployee.getDesignation());
            return employeeRepository.save(employee);
        }).orElse(null);
    }

    // DELETE employee
    @DeleteMapping("/employee/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
        return "Employee deleted with id: " + id;
    }
    @GetMapping("/")
public String testHome() {
    return "API is up and running!";
}

}
