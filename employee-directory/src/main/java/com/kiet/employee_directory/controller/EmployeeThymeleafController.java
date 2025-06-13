package com.kiet.employee_directory.controller;

import com.kiet.employee_directory.model.Employee;
import com.kiet.employee_directory.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EmployeeThymeleafController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public String listEmployees(Model model) {
        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "employees";  // maps to templates/employees.html
    }
}
