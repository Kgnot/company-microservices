package com.david.departmentservice.controller;

import com.david.departmentservice.client.EmployeeClient;
import com.david.departmentservice.model.Department;
import com.david.departmentservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeClient employeeClient;

    @GetMapping()
    public ResponseEntity<List<Department>> findAll(){
        List<Department> departments = departmentRepository.findAll();

        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> findById(@PathVariable Long id){
        Department department = departmentRepository.findById(id);

        return ResponseEntity.ok(department);
    }

    @PostMapping()
    public ResponseEntity<Department> add(@RequestBody Department department){
        Department newDepartment = departmentRepository.add(department);

        return new ResponseEntity<Department>(department, HttpStatus.CREATED);
    }

    @GetMapping("/with-employees")
    public ResponseEntity<List<Department>> findAllWithEmployees(){
        List<Department> departments = departmentRepository.findAll();

        departments.forEach(x -> x.setEmployees(employeeClient.findById(x.getId())));

        return ResponseEntity.ok(departments);
    }

}
