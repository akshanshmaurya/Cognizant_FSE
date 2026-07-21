package com.dsa.library;

import java.util.Arrays;

public class LibraryMain {
    public static void main(String[] args) {
        Book[] books = {
            new Book(1, "The Great Gatsby", "F. Scott Fitzgerald"),
            new Book(2, "To Kill a Mockingbird", "Harper Lee"),
            new Book(3, "1984", "George Orwell"),
            new Book(4, "Pride and Prejudice", "Jane Austen")
        };

        System.out.println("Testing Linear Search for '1984':");
        Book b1 = LibrarySearch.linearSearchByTitle(books, "1984");
        System.out.println("Found: " + b1);

        System.out.println("\nSorting books by Title for Binary Search...");
        Arrays.sort(books);

        System.out.println("Testing Binary Search for '1984':");
        Book b2 = LibrarySearch.binarySearchByTitle(books, "1984");
        System.out.println("Found: " + b2);
    }
}
