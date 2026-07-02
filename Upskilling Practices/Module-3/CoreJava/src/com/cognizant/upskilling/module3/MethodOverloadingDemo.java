package com.cognizant.upskilling.module3;

public class MethodOverloadingDemo {
    public static int add(int a, int b) {
        return a + b;
    }
    
    public static double add(double a, double b) {
        return a + b;
    }
    
    public static int add(int a, int b, int c) {
        return a + b + c;
    }
    
    public static void main(String[] args) {
        System.out.println("Sum of 10 and 20 (int): " + add(10, 20));
        System.out.println("Sum of 10.5 and 20.3 (double): " + add(10.5, 20.3));
        System.out.println("Sum of 10, 20, and 30 (int): " + add(10, 20, 30));
    }
}
