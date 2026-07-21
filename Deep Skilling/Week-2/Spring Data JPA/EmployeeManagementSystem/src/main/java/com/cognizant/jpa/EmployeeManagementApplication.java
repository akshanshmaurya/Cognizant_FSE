package com.cognizant.jpa;

import com.cognizant.jpa.entity.Department;
import com.cognizant.jpa.entity.Employee;
import com.cognizant.jpa.repository.DepartmentRepository;
import com.cognizant.jpa.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmployeeManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(EmployeeRepository empRepo, DepartmentRepository deptRepo) {
        return args -> {
            System.out.println("=== Spring Data JPA & Hibernate Demo ===");

            Department hr = deptRepo.save(new Department("HR"));
            Department it = deptRepo.save(new Department("IT"));

            empRepo.save(new Employee("Alice Johnson", "alice@company.com", 75000.0, hr));
            empRepo.save(new Employee("Bob Smith", "bob@company.com", 85000.0, it));
            empRepo.save(new Employee("Charlie Lee", "charlie@company.com", 55000.0, it));

            System.out.println("\nAll Employees:");
            empRepo.findAll().forEach(System.out::println);

            System.out.println("\nIT Department Employees (Derived Query):");
            empRepo.findByDepartmentName("IT").forEach(System.out::println);

            System.out.println("\nHigh Earners >= 70,000 (JPQL Query):");
            empRepo.findHighEarners(70000.0).forEach(System.out::println);
        };
    }
}
