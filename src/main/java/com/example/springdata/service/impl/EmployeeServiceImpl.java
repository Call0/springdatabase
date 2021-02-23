package com.example.springdata.service.impl;

import com.example.springdata.dto.EmployeeRequestDTO;
import com.example.springdata.dto.EmployeeResponseDTO;
import com.example.springdata.entity.Employee;
import com.example.springdata.repository.EmployeeRepository;
import com.example.springdata.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO requestDTO) {
        EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
        Employee employee = new Employee();
        BeanUtils.copyProperties(requestDTO, employee);
        Employee savedEmployee = employeeRepository.save(employee);
        BeanUtils.copyProperties(savedEmployee, responseDTO);
        return  responseDTO;
    }

    /*
    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {
        List<Employee> list = new ArrayList<>();
        EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
        Employee savedEmployee = new Employee();
        employeeRepository.findAll().forEach(list::add);
        for(int i=0; i<list.size(); i++){
            Employee emp = list.get(i);
            if(emp.getId().equals(id)){
                savedEmployee.setId(emp.getId());
                savedEmployee.setDepartmentName((emp.getDepartmentName()));
                savedEmployee.setName(emp.getName());
            }
        }
        BeanUtils.copyProperties(savedEmployee, responseDTO);
        return responseDTO;
    }
    */

    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {
        Optional<Employee> list = employeeRepository.findById(id);
        if(list.isPresent()){
            EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
            BeanUtils.copyProperties(list.get(), responseDTO);
            return responseDTO;
        }
        return null;
    }

    @Override
    public EmployeeResponseDTO updateEmployeeById(EmployeeRequestDTO requestDTO, Long id) {
        Optional<Employee> list = employeeRepository.findById(id);
        if(list.isPresent()){
            //update Employee
            /*
            Employee employee = new Employee();
            BeanUtils.copyProperties(requestDTO, employee);
            employeeRepository.save(employee);
            */
            EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
            Employee employeeFromDb = list.get();
            employeeFromDb.setName(requestDTO.getName());
            employeeFromDb.setDepartmentName(requestDTO.getDepartmentName());
            BeanUtils.copyProperties(employeeFromDb, responseDTO);
            employeeRepository.save(employeeFromDb);
            return responseDTO;
        }
        return null;
    }

    @Override
    public EmployeeResponseDTO deleteEmployeeById(Long id) {
        Optional<Employee> list = employeeRepository.findById(id);
        if(list.isPresent()) {
            EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
            BeanUtils.copyProperties(list.get(), responseDTO);
            employeeRepository.deleteById(id);
            return responseDTO;
        }
        return null;
    }
}