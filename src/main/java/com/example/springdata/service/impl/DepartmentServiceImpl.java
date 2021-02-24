package com.example.springdata.service.impl;

import com.example.springdata.dto.DepartmentRequestDTO;
import com.example.springdata.dto.DepartmentResponseDTO;
import com.example.springdata.entity.Department;
import com.example.springdata.entity.Employee;
import com.example.springdata.repository.DepartmentRepository;
import com.example.springdata.repository.EmployeeRepository;
import com.example.springdata.service.DepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

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


    @Override
    @Transactional
    public DepartmentResponseDTO updateDepartment(DepartmentRequestDTO departmentRequestDTO, Long id) {
        DepartmentResponseDTO departmentResponseDTO = new DepartmentResponseDTO();
        Department department = departmentRepository.findById(id).get();
        List<Employee> employeeList = employeeRepository.findByDepartment_Id(department.getId());
        department.setName(departmentRequestDTO.getName());
        departmentRepository.save(department);

        //append departmentCode to employee Code
        for (Employee employee : employeeList) {
            employee.setCode(departmentRequestDTO.getDepartmentCode());
            //employeeRepository.save(employee);
        }
        employeeRepository.saveAll(employeeList);
        BeanUtils.copyProperties(department, departmentResponseDTO);
        return departmentResponseDTO;
    }
}
