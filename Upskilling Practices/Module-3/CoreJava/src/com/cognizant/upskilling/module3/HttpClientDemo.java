package com.cognizant.upskilling.module3;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientDemo {
    public static void main(String[] args) {
        System.out.println("HTTP Client Fetching from public api (https://httpbin.org/get)...");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/get"))
                .GET()
                .build();
                
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response Status Code: " + response.statusCode());
            System.out.println("Response Body Summary: " + response.body().substring(0, Math.min(response.body().length(), 400)));
        } catch (Exception e) {
            System.out.println("HTTP client request failed (internet connection required). Error: " + e.getMessage());
        }
    }
}
