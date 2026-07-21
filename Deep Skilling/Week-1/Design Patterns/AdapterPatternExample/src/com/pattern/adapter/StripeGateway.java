package com.pattern.adapter;

public class StripeGateway {
    public void makePayment(double amount) {
        System.out.println("Processing $" + amount + " via Stripe Gateway.");
    }
}
