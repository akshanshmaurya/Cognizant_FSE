package com.cognizant.upskilling.module3;

public class OperatorPrecedenceDemo {
    public static void main(String[] args) {
        int a = 10;
        int b = 5;
        int c = 2;
        
        int result = a + b * c; 
        System.out.println("Expression: 10 + 5 * 2");
        System.out.println("Result: " + result);
        System.out.println("Explanation: Multiplicative operator (*) has higher precedence than additive (+).");
        
        int overriddenResult = (a + b) * c;
        System.out.println("Expression: (10 + 5) * 2");
        System.out.println("Result: " + overriddenResult);
        System.out.println("Explanation: Parentheses override precedence order.");
    }
}
