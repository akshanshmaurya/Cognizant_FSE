package com.cognizant.upskilling.module3;

import java.util.HashMap;
import java.util.Scanner;

public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<Integer, String> studentMap = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        
        studentMap.put(101, "Alice");
        studentMap.put(102, "Bob");
        studentMap.put(103, "Charlie");
        
        System.out.println("Prepopulated student map IDs: 101, 102, 103");
        System.out.print("Do you want to add a new student mapping? (yes/no): ");
        String addChoice = scanner.nextLine();
        
        if (addChoice.equalsIgnoreCase("yes") || addChoice.equalsIgnoreCase("y")) {
            System.out.print("Enter Student ID (Integer): ");
            int id = scanner.nextInt();
            scanner.nextLine(); // consume newline
            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine();
            studentMap.put(id, name);
        }
        
        System.out.print("\nEnter Student ID to retrieve: ");
        int queryId = scanner.nextInt();
        
        if (studentMap.containsKey(queryId)) {
            System.out.println("Student found with ID " + queryId + ": " + studentMap.get(queryId));
        } else {
            System.out.println("No student found with ID " + queryId);
        }
        
        scanner.close();
    }
}
