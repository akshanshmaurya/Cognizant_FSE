package com.dsa.sort;

import java.util.Arrays;

public class SortMain {
    public static void main(String[] args) {
        Order[] orders1 = {
            new Order("O101", "Alice", 250.00),
            new Order("O102", "Bob", 450.50),
            new Order("O103", "Charlie", 120.00),
            new Order("O104", "Diana", 890.20)
        };

        System.out.println("Original Orders:");
        Arrays.stream(orders1).forEach(System.out::println);

        System.out.println("\nSorted using Bubble Sort (O(N^2)):");
        SortAlgorithms.bubbleSort(orders1);
        Arrays.stream(orders1).forEach(System.out::println);

        Order[] orders2 = {
            new Order("O201", "Ethan", 550.00),
            new Order("O202", "Fiona", 150.75),
            new Order("O203", "George", 920.00)
        };

        System.out.println("\nSorted using Quick Sort (O(N log N)):");
        SortAlgorithms.quickSort(orders2, 0, orders2.length - 1);
        Arrays.stream(orders2).forEach(System.out::println);
    }
}
