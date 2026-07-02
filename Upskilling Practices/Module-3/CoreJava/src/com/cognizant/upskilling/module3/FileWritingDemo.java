package com.cognizant.upskilling.module3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileWritingDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string to write to file: ");
        String text = scanner.nextLine();
        
        // Write the string to a file named output.txt
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write(text);
            System.out.println("Success: Data successfully written to output.txt");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
