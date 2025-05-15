package com.david.employeeservice.repository;

import com.david.employeeservice.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {

    private static List<Employee> employees;

    static {
        employees = new ArrayList<>();
        employees.add(Employee.builder().id(1L).departmentId(4L).name("Juan").age(15).position("Gerente").build());
        employees.add(Employee.builder().id(2L).departmentId(1L).name("Sergio").age(25).position("No se xd").build());
        employees.add(Employee.builder().id(3L).departmentId(3L).name("Alberto").age(35).position("Vago").build());
        employees.add(Employee.builder().id(4L).departmentId(1L).name("Daniel").age(21).position("Estudiante").build());

    }


    public List<Employee> findByDepartmentId(Long departmentId) {
        return employees.stream().filter(employee -> employee.getDepartmentId().equals(departmentId)).collect(Collectors.toList());
    }

    public List<Employee> findAll() {
        return employees;
    }

    public Employee save(Employee newEmployee) {
        employees.add(newEmployee);
        return newEmployee;
    }
}
