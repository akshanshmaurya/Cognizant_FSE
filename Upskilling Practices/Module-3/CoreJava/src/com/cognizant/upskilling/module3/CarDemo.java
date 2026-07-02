package com.cognizant.upskilling.module3;

class Car {
    private String make;
    private String model;
    private int year;
    
    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }
    
    public void displayDetails() {
        System.out.println("Car Make: " + make + ", Model: " + model + ", Year: " + year);
    }
}

public class CarDemo {
    public static void main(String[] args) {
        Car car1 = new Car("Toyota", "Camry", 2022);
        Car car2 = new Car("Tesla", "Model 3", 2023);
        
        car1.displayDetails();
        car2.displayDetails();
    }
}
