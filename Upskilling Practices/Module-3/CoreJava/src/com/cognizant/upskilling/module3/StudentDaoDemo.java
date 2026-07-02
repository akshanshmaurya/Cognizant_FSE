package com.cognizant.upskilling.module3;

import java.sql.*;

class StudentDAO {
    private String dbUrl = "jdbc:sqlite:students.db";
    
    public void insertStudent(int id, String name, String email) {
        String sql = "INSERT OR IGNORE INTO students(id, name, email) VALUES(?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(dbUrl);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, email);
            pstmt.executeUpdate();
            System.out.println("Inserted student ID " + id + " using PreparedStatement.");
        } catch (SQLException e) {
            System.out.println("Insert error: " + e.getMessage());
        }
    }
    
    public void updateStudentName(int id, String newName) {
        String sql = "UPDATE students SET name = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(dbUrl);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newName);
            pstmt.setInt(2, id);
            int rows = pstmt.executeUpdate();
            System.out.println("Updated " + rows + " student record(s).");
        } catch (SQLException e) {
            System.out.println("Update error: " + e.getMessage());
        }
    }
}

public class StudentDaoDemo {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();
        dao.insertStudent(2, "Bob Smith", "bob@test.com");
        dao.updateStudentName(2, "Robert Smith");
    }
}
