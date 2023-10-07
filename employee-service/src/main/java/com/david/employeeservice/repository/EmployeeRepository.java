package com.david.employeeservice.repository;

import com.david.employeeservice.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {

    List<Employee> employees = new ArrayList<>();

    public Employee add(Employee department){
        employees.add(department);

        return department;
    }

    public List<Employee> findAll(){
        return employees;
    }

    public Employee findById(Long id){
        return employees.stream()
                .filter(x -> x.id() == id)
                .findFirst()
                .orElseThrow();
    }
    
}
