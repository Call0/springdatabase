package com.example.springdata.controller;

import com.example.springdata.dto.DepartmentRequestDTO;
import com.example.springdata.dto.DepartmentResponseDTO;
import com.example.springdata.entity.Department;
import com.example.springdata.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public DepartmentResponseDTO createDepartment(@RequestBody DepartmentRequestDTO departmentRequestDTO){
        return departmentService.createDepartment(departmentRequestDTO);
    }
}
