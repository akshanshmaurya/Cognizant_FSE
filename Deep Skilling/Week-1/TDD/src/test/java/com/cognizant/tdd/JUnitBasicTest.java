package com.cognizant.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JUnitBasicTest {
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        // Arrange step in fixture
        calculator = new Calculator();
    }

    @AfterEach
    void tearDown() {
        calculator = null;
    }

    @Test
    void testAssertionsAndAAA() {
        // Arrange
        int a = 5;
        int b = 3;

        // Act
        int sum = calculator.add(a, b);

        // Assert
        assertEquals(8, sum, "5 + 3 should equal 8");
        assertTrue(calculator.isEven(sum), "Sum should be even");
        assertFalse(calculator.isEven(7), "7 should not be even");
        assertNotNull(calculator, "Calculator instance should not be null");
    }
}
