package com.example.springdata.service;

import com.example.springdata.dto.EmployeeRequestDTO;
import com.example.springdata.dto.EmployeeResponseDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeResponseDTO createEmployee(EmployeeRequestDTO requestDTO);
    EmployeeResponseDTO getEmployeeById(Long id);
    EmployeeResponseDTO updateEmployeeById(EmployeeRequestDTO employeeRequestDTO, Long id);
    EmployeeResponseDTO deleteEmployeeById(Long id);

    List<EmployeeResponseDTO> getEmployeeListByDepartment(Long departmentId);
}
