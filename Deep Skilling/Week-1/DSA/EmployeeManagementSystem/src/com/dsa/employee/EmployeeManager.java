package com.dsa.employee;

public class EmployeeManager {
    private Employee[] employees;
    private int count;

    public EmployeeManager(int capacity) {
        employees = new Employee[capacity];
        count = 0;
    }

    // Add - O(1)
    public boolean addEmployee(Employee emp) {
        if (count < employees.length) {
            employees[count++] = emp;
            return true;
        }
        System.out.println("Array full! Cannot add " + emp.getEmployeeId());
        return false;
    }

    // Search - O(N)
    public Employee searchEmployee(int empId) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId() == empId) {
                return employees[i];
            }
        }
        return null;
    }

    // Delete - O(N)
    public boolean deleteEmployee(int empId) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId() == empId) {
                // Shift elements left
                for (int j = i; j < count - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[--count] = null;
                return true;
            }
        }
        return false;
    }

    // Traverse - O(N)
    public void traverse() {
        System.out.println("Employee Directory:");
        for (int i = 0; i < count; i++) {
            System.out.println(" - " + employees[i]);
        }
    }
}
