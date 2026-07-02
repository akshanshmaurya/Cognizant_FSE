package com.cognizant.upskilling.module3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LambdaDemo {
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();
        fruits.add("Orange");
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Grapes");
        
        System.out.println("Original list: " + fruits);
        
        // Sort using lambda expression
        Collections.sort(fruits, (s1, s2) -> s1.compareTo(s2));
        
        System.out.println("Sorted list (alphabetical using Lambda): " + fruits);
    }
}
