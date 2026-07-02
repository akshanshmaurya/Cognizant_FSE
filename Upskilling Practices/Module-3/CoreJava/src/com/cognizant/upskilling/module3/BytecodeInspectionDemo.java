package com.cognizant.upskilling.module3;

public class BytecodeInspectionDemo {
    public int multiply(int a, int b) {
        return a * b;
    }
    
    public static void main(String[] args) {
        System.out.println("Bytecode inspection target class.");
        System.out.println("Instructions to view bytecode:");
        System.out.println("1. Compile class: javac BytecodeInspectionDemo.java");
        System.out.println("2. Run javap tool: javap -c com.cognizant.upskilling.module3.BytecodeInspectionDemo");
    }
}
