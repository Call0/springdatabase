package com.example.springdata.repository;

import com.example.springdata.entity.Department;
import com.example.springdata.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    // by method name
    List<Employee> findByDepartment(Department department);

    //by @Query annotation


    //by native query

}
