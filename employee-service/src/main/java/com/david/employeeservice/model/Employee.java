package com.david.employeeservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {
    private Long id;
    private Long departmentId;
    private String name;
    private int age;
    String position;

}
