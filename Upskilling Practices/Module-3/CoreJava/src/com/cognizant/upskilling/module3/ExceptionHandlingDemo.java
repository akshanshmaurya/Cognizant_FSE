package com.cognizant.upskilling.module3;

import java.util.Scanner;

public class ExceptionHandlingDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter first integer: ");
            int num1 = scanner.nextInt();
            System.out.print("Enter second integer: ");
            int num2 = scanner.nextInt();
            
            int result = num1 / num2;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Exception Caught: Cannot divide by zero! Details: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception Caught: Invalid input or general error.");
        } finally {
            scanner.close();
        }
    }
}
