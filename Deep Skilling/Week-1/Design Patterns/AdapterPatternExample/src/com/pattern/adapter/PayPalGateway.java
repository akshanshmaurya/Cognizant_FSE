package com.pattern.adapter;

public class PayPalGateway {
    public void sendPayment(double amount) {
        System.out.println("Processing $" + amount + " via PayPal Gateway.");
    }
}
