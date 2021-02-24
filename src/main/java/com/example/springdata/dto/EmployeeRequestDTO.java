package com.example.springdata.dto;

import com.example.springdata.entity.Department;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequestDTO {

    private Long id;
    private String name;
    private String code;
    private DepartmentRequestDTO department;

}
