package com.dsa.search;

import java.util.Arrays;

public class SearchMain {
    public static void main(String[] args) {
        Product[] products = {
            new Product(105, "Wireless Mouse", "Electronics"),
            new Product(101, "Mechanical Keyboard", "Electronics"),
            new Product(108, "USB-C Hub", "Accessories"),
            new Product(102, "HD Monitor", "Electronics")
        };

        System.out.println("Testing Linear Search (O(N)):");
        Product res1 = SearchAlgorithms.linearSearch(products, 108);
        System.out.println("Found: " + res1);

        System.out.println("\nSorting array by Product ID for Binary Search...");
        Arrays.sort(products);

        System.out.println("Testing Binary Search (O(log N)):");
        Product res2 = SearchAlgorithms.binarySearch(products, 108);
        System.out.println("Found: " + res2);
    }
}
