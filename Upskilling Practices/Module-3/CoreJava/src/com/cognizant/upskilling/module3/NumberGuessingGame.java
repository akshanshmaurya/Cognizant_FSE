package com.cognizant.upskilling.module3;

import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Random random = new Random();
        int target = random.nextInt(100) + 1;
        Scanner scanner = new Scanner(System.in);
        int guess = 0;
        
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I have generated a random number between 1 and 100. Start guessing!");
        
        while (guess != target) {
            System.out.print("Enter your guess: ");
            guess = scanner.nextInt();
            
            if (guess > target) {
                System.out.println("Too high! Try again.");
            } else if (guess < target) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Congratulations! You guessed correctly. The number was " + target);
            }
        }
        scanner.close();
    }
}
