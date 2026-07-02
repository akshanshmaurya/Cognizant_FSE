package com.cognizant.upskilling.module3;

import java.util.Scanner;

public class ArraySumAverage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of elements: ");
        int size = scanner.nextInt();
        
        if (size <= 0) {
            System.out.println("Error: Size must be positive.");
            scanner.close();
            return;
        }
        
        double[] array = new double[size];
        double sum = 0;
        
        System.out.println("Enter array elements:");
        for (int i = 0; i < size; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            array[i] = scanner.nextDouble();
            sum += array[i];
        }
        
        double average = sum / size;
        System.out.println("Sum of elements: " + sum);
        System.out.println("Average of elements: " + average);
        scanner.close();
    }
}
