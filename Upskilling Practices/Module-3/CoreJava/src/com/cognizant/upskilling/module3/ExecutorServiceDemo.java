package com.cognizant.upskilling.module3;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceDemo {
    public static void main(String[] args) {
        System.out.println("Initializing Fixed Thread Pool of size 3...");
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        Callable<String> task1 = () -> {
            Thread.sleep(500);
            return "Result from Task 1";
        };
        
        Callable<String> task2 = () -> {
            Thread.sleep(800);
            return "Result from Task 2";
        };
        
        try {
            Future<String> future1 = executor.submit(task1);
            Future<String> future2 = executor.submit(task2);
            
            System.out.println("Submitted tasks. Fetching results...");
            System.out.println("Task 1: " + future1.get());
            System.out.println("Task 2: " + future2.get());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
            System.out.println("Executor Service shutdown completed.");
        }
    }
}
