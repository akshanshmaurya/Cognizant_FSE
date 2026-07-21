package com.dsa.task;

public class TaskMain {
    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();

        taskList.addTask(new Task(1, "Database Schema Design", "Completed"));
        taskList.addTask(new Task(2, "REST API Implementation", "In Progress"));
        taskList.addTask(new Task(3, "Unit Testing Setup", "Pending"));

        taskList.traverse();

        System.out.println("\nSearching for Task ID 2:");
        System.out.println(taskList.searchTask(2));

        System.out.println("\nDeleting Task ID 2...");
        taskList.deleteTask(2);
        taskList.traverse();
    }
}
