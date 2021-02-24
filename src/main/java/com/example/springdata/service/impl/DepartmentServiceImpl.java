package com.example.springdata.service.impl;

import com.example.springdata.dto.DepartmentRequestDTO;
import com.example.springdata.dto.DepartmentResponseDTO;
import com.example.springdata.entity.Department;
import com.example.springdata.repository.DepartmentRepository;
import com.example.springdata.service.DepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.jvm.hotspot.ui.DeadlockDetectionPanel;

import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentRequestDTO) {
        DepartmentResponseDTO departmentResponseDTO = new DepartmentResponseDTO();
        Department department = new Department();
        BeanUtils.copyProperties(departmentRequestDTO, department);
        departmentRepository.save(department);
        BeanUtils.copyProperties(department, departmentResponseDTO);
        return departmentResponseDTO;
    }

    @Override
    public Department getDepartment(Long id) {
        /*
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        Department department = new Department();
        if(optionalDepartment.isPresent()){
            BeanUtils.copyProperties(optionalDepartment.get(), department);
        }
        return department;
        */

        return departmentRepository.findById(id).get();
    }
}
