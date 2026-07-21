package com.dsa.task;

public class TaskLinkedList {
    private class Node {
        Task task;
        Node next;
        Node(Task task) { this.task = task; }
    }

    private Node head;

    // Add task at end - O(N) or O(1) with tail
    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        System.out.println("Added: " + task);
    }

    // Search task - O(N)
    public Task searchTask(int taskId) {
        Node temp = head;
        while (temp != null) {
            if (temp.task.getTaskId() == taskId) {
                return temp.task;
            }
            temp = temp.next;
        }
        return null;
    }

    // Delete task - O(N)
    public boolean deleteTask(int taskId) {
        if (head == null) return false;

        if (head.task.getTaskId() == taskId) {
            head = head.next;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.task.getTaskId() == taskId) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Traverse list - O(N)
    public void traverse() {
        System.out.println("TaskList (Singly Linked List):");
        Node temp = head;
        while (temp != null) {
            System.out.println(" -> " + temp.task);
            temp = temp.next;
        }
    }
}
