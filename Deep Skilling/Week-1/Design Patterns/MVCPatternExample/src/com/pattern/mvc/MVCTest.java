package com.pattern.mvc;

public class MVCTest {
    public static void main(String[] args) {
        Student student = new Student("S101", "Alice", "A");
        StudentView view = new StudentView();

        StudentController controller = new StudentController(student, view);
        controller.updateView();

        System.out.println("\nUpdating student grade...");
        controller.setStudentGrade("A+");
        controller.updateView();
    }
}
