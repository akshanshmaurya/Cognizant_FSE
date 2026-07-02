package com.cognizant.upskilling.module3;

class Animal {
    public void makeSound() {
        System.out.println("Generic animal sound");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Bark");
    }
}

public class InheritanceDemo {
    public static void main(String[] args) {
        Animal myAnimal = new Animal();
        Dog myDog = new Dog();
        
        System.out.print("Animal sound: ");
        myAnimal.makeSound();
        
        System.out.print("Dog sound: ");
        myDog.makeSound();
    }
}
