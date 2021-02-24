package com.example.springdata.service;

import com.example.springdata.dto.EmployeeRequestDTO;
import com.example.springdata.dto.EmployeeResponseDTO;
import com.example.springdata.entity.Employee;

import java.util.List;

public interface EmployeeService {
    EmployeeResponseDTO createEmployee(EmployeeRequestDTO requestDTO);
    EmployeeResponseDTO getEmployeeById(Long id);
    EmployeeResponseDTO updateEmployeeById(EmployeeRequestDTO employeeRequestDTO, Long id);
    EmployeeResponseDTO deleteEmployeeById(Long id);

    List<EmployeeResponseDTO> getEmployeeListByDepartment(Long departmentId);

    List<Employee> getEmployeeWithMostExperience();
}
