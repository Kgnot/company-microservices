package com.david.employeeservice.controller;

import com.david.employeeservice.model.Employee;
import com.david.employeeservice.repository.EmployeeRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public ResponseEntity<List<Employee>> findAll(){
        List<Employee> employees = employeeRepository.findAll();

        return ResponseEntity.ok(employees);
    }


}
