package com.cognizant.upskilling.module3;

import java.util.Arrays;
import java.util.List;

// Record keyword for immutable data structures (Java 16+)
record Person(String name, int age) {}

public class RecordsDemo {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
            new Person("Alice", 25),
            new Person("Bob", 17),
            new Person("Charlie", 32),
            new Person("Diana", 15)
        );
        
        System.out.println("All people records:");
        people.forEach(System.out::println);
        
        System.out.println("\nFiltering adults (age >= 18) using Streams:");
        people.stream()
              .filter(p -> p.age() >= 18)
              .forEach(p -> System.out.println("- " + p.name() + " (" + p.age() + " years old)"));
    }
}
