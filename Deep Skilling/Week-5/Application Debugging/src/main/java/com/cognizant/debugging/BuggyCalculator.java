package com.cognizant.debugging;

public class BuggyCalculator {

    public static void main(String[] args) {
        System.out.println("Starting Debugging Demo...");
        
        int a = 10;
        int b = 0;
        
        try {
            int result = divide(a, b);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            // Instruction: Place a breakpoint on the next line to inspect the exception
            e.printStackTrace();
        }
        
        int[] numbers = {1, 2, 3, 4, 5};
        int sum = calculateSum(numbers);
        System.out.println("Sum of array elements: " + sum);
    }

    public static int divide(int numerator, int denominator) {
        // Instruction: Place a breakpoint here to debug ArithmeticException
        return numerator / denominator;
    }

    public static int calculateSum(int[] arr) {
        int sum = 0;
        // Logical error: Loop condition should be i < arr.length, not i <= arr.length
        // Instruction: Use debugger to trace the ArrayIndexOutOfBoundsException
        for (int i = 0; i < arr.length; i++) { // Fixed logic for successful compile/run
            sum += arr[i];
        }
        return sum;
    }
}
