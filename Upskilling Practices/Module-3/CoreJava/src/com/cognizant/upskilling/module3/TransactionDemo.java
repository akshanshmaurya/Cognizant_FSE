package com.cognizant.upskilling.module3;

import java.sql.*;

public class TransactionDemo {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:students.db";
        System.out.println("Simulating safe transaction handling for balance transfers...");
        
        try (Connection conn = DriverManager.getConnection(url)) {
            // Create sample accounts
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("CREATE TABLE IF NOT EXISTS accounts (id INTEGER PRIMARY KEY, name TEXT, balance DOUBLE)");
                stmt.execute("DELETE FROM accounts"); // clear for demo
                stmt.execute("INSERT INTO accounts (id, name, balance) VALUES (1, 'Alice', 1000.00)");
                stmt.execute("INSERT INTO accounts (id, name, balance) VALUES (2, 'Bob', 500.00)");
            }
            
            // Start transaction
            conn.setAutoCommit(false);
            
            try {
                // Debit Alice $200
                String debitSql = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(debitSql)) {
                    pstmt.setDouble(1, 200.00);
                    pstmt.setInt(2, 1);
                    pstmt.executeUpdate();
                }
                
                // Credit Bob $200
                String creditSql = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(creditSql)) {
                    pstmt.setDouble(1, 200.00);
                    pstmt.setInt(2, 2);
                    pstmt.executeUpdate();
                }
                
                // Commit changes
                conn.commit();
                System.out.println("Transaction Committed Successfully!");
                
            } catch (SQLException e) {
                // Rollback on error
                conn.rollback();
                System.out.println("Transaction Rollback executed due to error: " + e.getMessage());
            }
            
            // Print final balances
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM accounts")) {
                while (rs.next()) {
                    System.out.println("Account: " + rs.getString("name") + ", Balance: " + rs.getDouble("balance"));
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
