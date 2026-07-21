package com.pattern.observer;

public class WebApp implements Observer {
    @Override
    public void update(String stockName, double price) {
        System.out.println("Web Dashboard: " + stockName + " live ticker updated to $" + price);
    }
}
