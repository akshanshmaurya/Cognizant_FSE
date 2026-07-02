package com.cognizant.upskilling.module3;

public class PatternMatchingSwitchDemo {
    public static void inspectObject(Object obj) {
        // Pattern Matching for switch (Java 21)
        String description = switch (obj) {
            case null -> "The object is null.";
            case Integer i -> "It is an Integer: " + i + " (square: " + (i * i) + ")";
            case Double d -> "It is a Double: " + d;
            case String s -> "It is a String: '" + s + "' (length: " + s.length() + ")";
            case Boolean b -> "It is a Boolean: " + b;
            default -> "Unknown type: " + obj.getClass().getName();
        };
        System.out.println(description);
    }
    
    public static void main(String[] args) {
        inspectObject("Hello Java 21");
        inspectObject(42);
        inspectObject(3.14159);
        inspectObject(true);
        inspectObject(null);
    }
}
