package com.subscription.model;

public class User {
    private int userId;       // optional if auto-increment in DB
    private String name;
    private String email;

    // Default constructor
    public User() {
    }

    // Constructor without userId (for insertion)
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Constructor with userId (for fetching from DB)
    public User(int userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User [ID=" + userId + ", Name=" + name + ", Email=" + email + "]";
    }

}
