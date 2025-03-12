package com.david.departmentservice.repository;

import com.david.departmentservice.model.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public interface DepartmentRepository extends MongoRepository<Department, Long> {

}
