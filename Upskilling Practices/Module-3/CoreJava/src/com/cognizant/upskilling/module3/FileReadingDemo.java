package com.cognizant.upskilling.module3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReadingDemo {
    public static void main(String[] args) {
        System.out.println("Reading contents from output.txt:");
        try (BufferedReader reader = new BufferedReader(new FileReader("output.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file output.txt. Verify that FileWritingDemo has been run first. Details: " + e.getMessage());
        }
    }
}
