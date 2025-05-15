package com.david.departmentservice.repository;

import com.david.departmentservice.model.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class DepartmentRepository {

    private static final List<Department> departments = new ArrayList<>();

    static {
        departments.add(Department.builder().id(1L).name("IT").build());
        departments.add(Department.builder().id(2L).name("Finance").build());
        departments.add(Department.builder().id(3L).name("Marketing").build());
        departments.add(Department.builder().id(4L).name("HR").build());
    }

    public List<Department> findAll() {
        return new ArrayList<>(departments); // Para evitar modificar la lista original desde fuera
    }

    public Optional<Department> findById(Long id) {
        return departments.stream()
                .filter(department -> department.getId().equals(id))
                .findFirst();
    }

    public Department save(Department newDepartment) {
        // Si ya existe, lo reemplazamos
        departments.removeIf(dep -> dep.getId().equals(newDepartment.getId()));
        departments.add(newDepartment);
        return newDepartment;
    }
}
