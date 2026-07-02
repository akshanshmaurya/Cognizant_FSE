package com.cognizant.upskilling.module3;

public class TypeCastingDemo {
    public static void main(String[] args) {
        // Explicit casting: double to int
        double doubleVal = 9.78;
        int intVal = (int) doubleVal;
        System.out.println("Double value: " + doubleVal);
        System.out.println("Casted Int value (loss of fraction): " + intVal);
        
        // Implicit casting: int to double
        int originalInt = 100;
        double castedDouble = originalInt;
        System.out.println("Original Int value: " + originalInt);
        System.out.println("Implicitly casted Double value: " + castedDouble);
    }
}
