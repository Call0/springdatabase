package com.example.springdata.controller;

import com.example.springdata.dto.EmployeeRequestDTO;
import com.example.springdata.dto.EmployeeResponseDTO;
import com.example.springdata.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private  EmployeeService employeeService;

    //POST - /employee
    @PostMapping
    public EmployeeResponseDTO createEmployee(@RequestBody EmployeeRequestDTO requestDTO){
        return employeeService.createEmployee(requestDTO);
    }


    //GET - /employee/{id}
    @GetMapping(path = "/{id}")
    public EmployeeResponseDTO getEmployeeById(@PathVariable ("id") Long id){
        return employeeService.getEmployeeById(id);
    }


    //PUT - /employee/{id}
    @PutMapping(path = "/{id}")
    public EmployeeResponseDTO updateEmployeeById(@RequestBody EmployeeRequestDTO requestDTO, @PathVariable ("id") Long id){
        return employeeService.updateEmployeeById(requestDTO, id);
    }


    //DELETE - /employee/{id}
    @DeleteMapping(path = "/{id}")
    public EmployeeResponseDTO deleteEmployeeById(@PathVariable ("id") Long id){
        return employeeService.deleteEmployeeById(id);
    }


    //GET - /employee/department/{id}
    @GetMapping(path="department/{id}")
    public List<EmployeeResponseDTO> getEmployeeListByDepartment(@PathVariable("id") Long departmentId){
        return  employeeService.getEmployeeListByDepartment(departmentId);
    }
}
