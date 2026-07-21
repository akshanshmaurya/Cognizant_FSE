package com.cognizant.tdd;

public class MyService {
    private ExternalApi api;

    public MyService(ExternalApi api) {
        this.api = api;
    }

    public String fetchData() {
        return api.getData();
    }

    public String fetchDataById(String id) {
        return api.getDataById(id);
    }

    public void notifyUser(String email, String message) {
        api.sendNotification(email, message);
    }
}
