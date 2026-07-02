package com.cognizant.upskilling.module3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConnectionDemo {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:students.db";
        System.out.println("Attempting basic JDBC connection (SQLite file-based)...");
        
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("Connected to SQLite database successfully!");
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute("CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY, name TEXT, email TEXT)");
                    stmt.execute("INSERT OR IGNORE INTO students (id, name, email) VALUES (1, 'Alice', 'alice@test.com')");
                    
                    ResultSet rs = stmt.executeQuery("SELECT * FROM students");
                    System.out.println("Retrieved student records:");
                    while (rs.next()) {
                        System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Email: " + rs.getString("email"));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Database connectivity error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("SQLite driver not found or loaded (requires JDBC library). Error: " + e.getMessage());
        }
    }
}
