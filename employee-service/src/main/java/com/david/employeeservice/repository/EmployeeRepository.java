package com.david.employeeservice.repository;

import com.david.employeeservice.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Long> {
    List<Employee> findByDepartmentId(Long departmentId);
}
