package com.david.employeeservice.controller;

import com.david.employeeservice.model.Employee;
import com.david.employeeservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        log.info("Find all employees");
        List<Employee> employees = employeeRepository.findAll();

        return ResponseEntity.ok(employees);
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<List<Employee>> findById(@PathVariable Long id) {
        log.info("Find employee by department id: {}", id);
        List<Employee> employee = employeeRepository.findByDepartmentId(id);

        return ResponseEntity.ok(employee);
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        log.info("Create employee: {}", employee);
        Employee added = employeeRepository.save(employee);

        return ResponseEntity.ok(added);
    }


}
