package com.example.springdata.repository;

import com.example.springdata.entity.Department;
import com.example.springdata.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    // by method name
    List<Employee> findByDepartment(Department department);

    List<Employee> findByDepartment_Id(Long departmentId);


    //by @Query annotation
    // @Query("SELECT e FROM Employee (Java class name, not SQL table name) e WHERE e.department.id = ?1")
    @Query("FROM Employee e WHERE e.department.id = ?1")
    List<Employee> getEmployeeListByDepartmentId(Long departmentId);

    @Query("FROM Employee e WHERE e.department.id = :departmentId")
    List<Employee> getEmployeeListByDepartmentIdParam(@Param("departmentId") Long departmentId);

    //by native query
    // @Query("SELECT e FROM Employee (SQL table name, not java class name) e WHERE e.department.id = ?1")
    @Query(value = "SELECT * FROM employee e WHERE e.department_id = ?1", nativeQuery = true)
    List<Employee> getEmployeeListByNativeQuery(Long departmentId);

    @Query(value = "SELECT * FROM employee e WHERE e.years_of_experience IN (SELECT MAX(years_of_experience) FROM employee)", nativeQuery = true)
    List<Employee> getMostExperiencedEmployee();
}
