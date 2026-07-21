package com.pattern.observer;

public class ObserverTest {
    public static void main(String[] args) {
        StockMarket techStock = new StockMarket("AAPL", 180.00);

        Observer mobile = new MobileApp("John");
        Observer web = new WebApp();

        techStock.registerObserver(mobile);
        techStock.registerObserver(web);

        System.out.println("Updating price to $185.50:");
        techStock.setPrice(185.50);
    }
}
