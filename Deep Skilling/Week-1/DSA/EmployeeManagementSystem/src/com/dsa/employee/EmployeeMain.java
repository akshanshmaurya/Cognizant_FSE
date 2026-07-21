package com.dsa.employee;

public class EmployeeMain {
    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager(5);

        manager.addEmployee(new Employee(1, "Alice", "Manager", 75000));
        manager.addEmployee(new Employee(2, "Bob", "Developer", 60000));
        manager.addEmployee(new Employee(3, "Charlie", "Designer", 55000));

        manager.traverse();

        System.out.println("\nSearching for Employee ID 2:");
        System.out.println(manager.searchEmployee(2));

        System.out.println("\nDeleting Employee ID 2...");
        manager.deleteEmployee(2);
        manager.traverse();
    }
}
