package com.david.departmentservice.repository;

import com.david.departmentservice.model.Department;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class DepartmentRepository {

    List<Department> departments = new ArrayList<>();

    public Department add(Department department){
        departments.add(department);

        return department;
    }

    public List<Department> findAll(){
        return departments;
    }

    public Department findById(Long id){
        return departments.stream()
                .filter(x -> Objects.equals(x.getId(), id))
                .findFirst()
                .orElseThrow();
    }

}
