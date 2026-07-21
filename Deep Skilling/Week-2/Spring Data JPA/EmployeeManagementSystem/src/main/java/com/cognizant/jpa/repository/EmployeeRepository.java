package com.cognizant.jpa.repository;

import com.cognizant.jpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    // Derived Query Method
    List<Employee> findByDepartmentName(String deptName);

    // HQL/JPQL Query
    @Query("SELECT e FROM Employee e WHERE e.salary >= :minSalary")
    List<Employee> findHighEarners(@Param("minSalary") Double minSalary);

    // Native SQL Query
    @Query(value = "SELECT * FROM employees WHERE email LIKE %:domain%", nativeQuery = true)
    List<Employee> findByEmailDomain(@Param("domain") String domain);
}
