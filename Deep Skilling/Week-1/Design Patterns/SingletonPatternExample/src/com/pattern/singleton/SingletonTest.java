package com.pattern.singleton;

public class SingletonTest {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log("Testing Singleton Pattern.");

        if (logger1 == logger2) {
            System.out.println("SUCCESS: Both logger instances are identical (Same HashCode: " + logger1.hashCode() + ").");
        } else {
            System.out.println("FAILURE: Logger instances are different.");
        }
    }
}
