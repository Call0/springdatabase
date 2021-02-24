package com.example.springdata.controller;

import com.example.springdata.dto.DepartmentRequestDTO;
import com.example.springdata.dto.DepartmentResponseDTO;
import com.example.springdata.entity.Department;
import com.example.springdata.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public DepartmentResponseDTO createDepartment(@RequestBody DepartmentRequestDTO departmentRequestDTO){
        return departmentService.createDepartment(departmentRequestDTO);
    }

    @GetMapping(path="/{id}")
    public Department getDepartment(@PathVariable("id") Long id){
        return departmentService.getDepartment(id);
    }

    @PutMapping(path="/{id}" )
    public DepartmentResponseDTO updateDepartment(@RequestBody DepartmentRequestDTO departmentRequestDTO, @PathVariable("id") Long id){
        return departmentService.updateDepartment(departmentRequestDTO, id);
    }
}
