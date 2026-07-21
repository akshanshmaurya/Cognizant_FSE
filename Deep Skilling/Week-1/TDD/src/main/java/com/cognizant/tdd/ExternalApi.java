package com.cognizant.tdd;

public interface ExternalApi {
    String getData();
    String getDataById(String id);
    void sendNotification(String recipient, String msg);
}
