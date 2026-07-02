package com.cognizant.upskilling.module3;

import java.io.*;
import java.net.*;

public class TcpChatDemo {
    public static void main(String[] args) {
        System.out.println("TCP Client-Server Chat Demo. Spawning client and server on separate threads...");
        
        // Spawn Server Thread
        Thread serverThread = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(5000)) {
                System.out.println("Server: Listening on port 5000...");
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                     
                    System.out.println("Server: Connection accepted.");
                    String message = in.readLine();
                    System.out.println("Server Received: " + message);
                    out.println("Server Echo: " + message);
                }
            } catch (IOException e) {
                System.out.println("Server error: " + e.getMessage());
            }
        });
        serverThread.start();
        
        // Let server start listening
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        
        // Spawning Client Thread
        Thread clientThread = new Thread(() -> {
            try (Socket socket = new Socket("localhost", 5000);
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                 
                System.out.println("Client: Connecting to server...");
                out.println("Hello, Server! This is a test chat message.");
                String response = in.readLine();
                System.out.println("Client Received response: " + response);
            } catch (IOException e) {
                System.out.println("Client error: " + e.getMessage());
            }
        });
        clientThread.start();
        
        try {
            serverThread.join();
            clientThread.join();
        } catch (InterruptedException e) {
            System.out.println("Chat execution interrupted.");
        }
    }
}
