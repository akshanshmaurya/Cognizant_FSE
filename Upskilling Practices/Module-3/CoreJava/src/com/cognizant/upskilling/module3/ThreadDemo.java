package com.cognizant.upskilling.module3;

class CustomRunnable implements Runnable {
    private String threadName;
    
    public CustomRunnable(String name) {
        this.threadName = name;
    }
    
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(threadName + " is executing loop step " + i);
            try {
                Thread.sleep(500); // Sleep 500ms
            } catch (InterruptedException e) {
                System.out.println(threadName + " was interrupted.");
            }
        }
        System.out.println(threadName + " finished execution.");
    }
}

public class ThreadDemo {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new CustomRunnable("Thread A"));
        Thread thread2 = new Thread(new CustomRunnable("Thread B"));
        
        System.out.println("Starting threads...");
        thread1.start();
        thread2.start();
    }
}
