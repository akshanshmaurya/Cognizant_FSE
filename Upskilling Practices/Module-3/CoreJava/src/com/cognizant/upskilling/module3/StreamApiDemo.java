package com.cognizant.upskilling.module3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamApiDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("Original numbers list: " + numbers);
        
        // Stream API to filter even numbers and collect
        List<Integer> evens = numbers.stream()
                                     .filter(n -> n % 2 == 0)
                                     .collect(Collectors.toList());
                                     
        System.out.println("Filtered even numbers: " + evens);
    }
}
