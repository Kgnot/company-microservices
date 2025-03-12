package com.david.employeeservice.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "employee")
public class Employee {
    @Id
    private Long id;
    private Long departmentId;
    private String name;
    private int age;
    String position;

}
