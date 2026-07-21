package com.cognizant.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private Double salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee() {}
    public Employee(String name, String email, Double salary, Department department) {
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.department = department;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Double getSalary() { return salary; }
    public Department getDepartment() { return department; }

    @Override
    public String toString() {
        return "Employee [ID=" + id + ", Name='" + name + "', Email='" + email + "', Salary=$" + salary + 
               ", Dept=" + (department != null ? department.getName() : "N/A") + "]";
    }
}
