package com.example.springdata.service;

import com.example.springdata.dto.DepartmentRequestDTO;
import com.example.springdata.dto.DepartmentResponseDTO;

public interface DepartmentService {
    DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentRequestDTO);
}
