package com.example.springdata.service;

import com.example.springdata.dto.EmployeeRequestDTO;
import com.example.springdata.dto.EmployeeResponseDTO;

public interface EmployeeService {
    EmployeeResponseDTO createEmployee(EmployeeRequestDTO requestDTO);
    EmployeeResponseDTO getEmployeeById(Long id);
    EmployeeResponseDTO updateEmployeeById(EmployeeRequestDTO employeeRequestDTO, Long id);
    EmployeeResponseDTO deleteEmployeeById(Long id);
}
