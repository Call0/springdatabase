package com.example.springdata.service;

import com.example.springdata.dto.DepartmentRequestDTO;
import com.example.springdata.dto.DepartmentResponseDTO;
import com.example.springdata.entity.Department;

public interface DepartmentService {

    DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentRequestDTO);

    Department getDepartment(Long id);

    DepartmentResponseDTO updateDepartment(DepartmentRequestDTO departmentRequestDTO, Long id);
}
