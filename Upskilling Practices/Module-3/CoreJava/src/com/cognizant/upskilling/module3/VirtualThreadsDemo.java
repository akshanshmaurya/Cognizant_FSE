package com.cognizant.upskilling.module3;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

public class VirtualThreadsDemo {
    public static void main(String[] args) {
        System.out.println("Launching 10,000 virtual threads (Java 21 lightweight concurrency)...");
        AtomicInteger counter = new AtomicInteger(0);
        
        Instant start = Instant.now();
        
        Thread[] threads = new Thread[10000];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = Thread.startVirtualThread(() -> {
                counter.incrementAndGet();
                try {
                    Thread.sleep(Duration.ofMillis(50));
                } catch (InterruptedException e) {}
            });
        }
        
        // Wait for all to finish
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {}
        }
        
        Instant end = Instant.now();
        System.out.println("Successfully ran " + counter.get() + " virtual threads in " + Duration.between(start, end).toMillis() + " ms");
    }
}
