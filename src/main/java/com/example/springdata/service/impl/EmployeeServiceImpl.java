package com.example.springdata.service.impl;


import com.example.springdata.dto.EmployeeRequestDTO;
import com.example.springdata.dto.EmployeeResponseDTO;
import com.example.springdata.entity.Department;
import com.example.springdata.entity.Employee;
import com.example.springdata.repository.DepartmentRepository;
import com.example.springdata.repository.EmployeeRepository;
import com.example.springdata.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO requestDTO) {
        Employee employee = new Employee();
        Optional<Department> departmentOptional = departmentRepository.findById(requestDTO.getDepartment().getId());
        if(departmentOptional.isPresent()){
            employee.setDepartment(departmentOptional.get());
        } else{
            Department department = new Department();
            department.setName(requestDTO.getDepartment().getName());
            employee.setDepartment(department);
        }
        EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
        BeanUtils.copyProperties(requestDTO, employee);
        Employee savedEmployee = employeeRepository.save(employee);
        BeanUtils.copyProperties(savedEmployee, responseDTO);

        responseDTO.setDepartmentFromEntity(savedEmployee.getDepartment());

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
            responseDTO.setDepartmentFromEntity(list.get().getDepartment());
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

            Optional<Department> departmentOptional = departmentRepository.findById(requestDTO.getDepartment().getId());
            if(departmentOptional.isPresent()){
                employeeFromDb.setDepartment(departmentOptional.get());
            } else{
                Department department = new Department();
                department.setName(requestDTO.getDepartment().getName());
                employeeFromDb.setDepartment(department);
            }

            BeanUtils.copyProperties(employeeFromDb, responseDTO);
            employeeRepository.save(employeeFromDb);

            responseDTO.setDepartmentFromEntity(employeeFromDb.getDepartment());
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


    @Override
    public List<EmployeeResponseDTO> getEmployeeListByDepartment(Long departmentId) {
        //Department department = departmentRepository.findById(departmentId).get();
        //List<Employee> employeeList = employeeRepository.findByDepartment(department);
        //List<Employee> employeeList = employeeRepository.findByDepartment_Id(departmentId);
        //List<Employee> employeeList = employeeRepository.getEmployeeListByDepartmentId(departmentId);
        //List<Employee> employeeList = employeeRepository.getEmployeeListByDepartmentIdParam(departmentId);
        List<Employee> employeeList = employeeRepository.getEmployeeListByNativeQuery(departmentId);
        List<EmployeeResponseDTO> employeeResponseDTOList = new ArrayList<>();
        for (Employee employee : employeeList) {
            EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
            BeanUtils.copyProperties(employee, employeeResponseDTO);
            employeeResponseDTO.setDepartmentFromEntity(employee.getDepartment());
            employeeResponseDTOList.add(employeeResponseDTO);
        }
        return employeeResponseDTOList;
    }

    @Override
    public List<Employee> getEmployeeWithMostExperience() {
        List<Employee> employeeList;
        employeeList = employeeRepository.getMostExperiencedEmployee();
        return employeeList;
    }
}