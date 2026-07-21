package com.cognizant.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingExample {
    private static final Logger logger = LoggerFactory.getLogger(LoggingExample.class);

    public static void main(String[] args) {
        // Exercise 1: Logging Error Messages and Warning Levels
        logger.info("Initializing SLF4J and Logback Logging Application...");
        logger.warn("This is a warning message indicating potential risk.");
        logger.error("This is an error message indicating a system failure!");

        // Exercise 2: Parameterized Logging
        String user = "Alice";
        int attempts = 3;
        logger.info("User {} failed login attempt #{}", user, attempts);
        logger.error("Failed to connect to database URL: {} with timeout: {}s", "jdbc:mysql://localhost:3306/db", 5);

        // Exercise 3: Multiple Appenders (Console & app.log)
        logger.debug("Debug level log entry generated for file and console appenders.");
        System.out.println("Logging complete. Check console and 'app.log' file for output.");
    }
}
