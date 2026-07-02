package com.cognizant.upskilling.module3;

import java.util.Scanner;

public class StringReversal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String str = scanner.nextLine();
        
        StringBuilder reversed = new StringBuilder(str).reverse();
        System.out.println("Reversed String: " + reversed.toString());
        scanner.close();
    }
}
