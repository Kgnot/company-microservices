package com.david.departmentservice.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "department")
public class Department {
    @Id
    private Long id;
    private String name;
    List<Employee> employees;

}
