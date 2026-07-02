package com.cognizant.upskilling.module3;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListDemo {
    public static void main(String[] args) {
        ArrayList<String> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String choice = "yes";
        
        while (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
            System.out.print("Enter a student name: ");
            String name = scanner.nextLine();
            students.add(name);
            
            System.out.print("Do you want to add another student? (yes/no): ");
            choice = scanner.nextLine();
        }
        
        System.out.println("\nStudent list entered:");
        for (String student : students) {
            System.out.println("- " + student);
        }
        scanner.close();
    }
}
