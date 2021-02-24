package com.example.springdata.dto;

import com.example.springdata.entity.Department;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponseDTO {

    private Long id;
    private String name;
    private String code;
    private Integer yearsOfExperience;
    private DepartmentResponseDTO department;


    public void setDepartmentFromEntity(Department departmentFromEntity){
        DepartmentResponseDTO departmentResponseDTO = new DepartmentResponseDTO();
        departmentResponseDTO.setId(departmentFromEntity.getId());
        departmentResponseDTO.setName(departmentFromEntity.getName());
        this.department = departmentResponseDTO;
    }
}
