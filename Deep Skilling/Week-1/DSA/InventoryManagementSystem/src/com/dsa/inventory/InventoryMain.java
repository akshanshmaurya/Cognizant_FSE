package com.dsa.inventory;

public class InventoryMain {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();

        manager.addProduct(new Product("P101", "Laptop", 10, 999.99));
        manager.addProduct(new Product("P102", "Smartphone", 25, 499.99));
        manager.displayAll();

        System.out.println("\nUpdating Laptop price & quantity...");
        manager.updateProduct("P101", 12, 949.99);

        System.out.println("\nDeleting Smartphone...");
        manager.deleteProduct("P102");
        manager.displayAll();
    }
}
