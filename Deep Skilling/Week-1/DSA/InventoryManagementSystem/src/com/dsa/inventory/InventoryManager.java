package com.dsa.inventory;

import java.util.HashMap;
import java.util.Map;

/**
 * Time Complexity Analysis:
 * - Add Product: O(1) average time complexity using HashMap.
 * - Update Product: O(1) average time complexity.
 * - Delete Product: O(1) average time complexity.
 * HashMap is chosen over ArrayList for efficient O(1) lookup by Product ID.
 */
public class InventoryManager {
    private Map<String, Product> inventory = new HashMap<>();

    public void addProduct(Product p) {
        inventory.put(p.getProductId(), p);
        System.out.println("Added: " + p);
    }

    public void updateProduct(String productId, int newQty, double newPrice) {
        Product p = inventory.get(productId);
        if (p != null) {
            p.setQuantity(newQty);
            p.setPrice(newPrice);
            System.out.println("Updated: " + p);
        } else {
            System.out.println("Product ID " + productId + " not found!");
        }
    }

    public void deleteProduct(String productId) {
        Product removed = inventory.remove(productId);
        if (removed != null) {
            System.out.println("Deleted: " + removed);
        } else {
            System.out.println("Product ID " + productId + " not found!");
        }
    }

    public void displayAll() {
        System.out.println("Current Inventory:");
        inventory.values().forEach(p -> System.out.println(" - " + p));
    }
}
