package com.david.employeeservice.controller;

import com.david.employeeservice.model.Employee;
import com.david.employeeservice.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Slf4j
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    //private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public ResponseEntity<List<Employee>> findAll(){
        log.info("Find all employees");
        List<Employee> employees = employeeRepository.findAll();

        return ResponseEntity.ok(employees);
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<List<Employee>> findById(@PathVariable Long id){
        log.info("Find employee by department id: {}", id);
        List<Employee> employee = employeeRepository.findByDepartmentId(id);

        return ResponseEntity.ok(employee);
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee){
        log.info("Create employee: {}", employee);
        Employee added = employeeRepository.add(employee);

        return ResponseEntity.ok(added);
    }


}
