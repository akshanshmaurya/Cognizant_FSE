package com.pattern.proxy;

public class ProxyTest {
    public static void main(String[] args) {
        Image img1 = new ProxyImage("photo1.jpg");

        System.out.println("--- First display call ---");
        img1.display(); // Loads from server

        System.out.println("\n--- Second display call ---");
        img1.display(); // Uses cache
    }
}
