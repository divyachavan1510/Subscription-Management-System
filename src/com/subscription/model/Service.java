package com.subscription.model;

public class Service {
    private int serviceId;       // optional if auto-increment in DB
    private String serviceName;
    private double price;

    // Default constructor
    public Service() {
    }

    // Constructor without serviceId (for insertion)
    public Service(String serviceName, double price) {
        this.serviceName = serviceName;
        this.price = price;
    }

    // Constructor with serviceId (for fetching from DB)
    public Service(int serviceId, String serviceName, double price) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.price = price;
    }

    // Getters and Setters
    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // For easy printing
    @Override
    public String toString() {
        return "Service [ID=" + serviceId + ", Name=" + serviceName + ", Price=" + price + "]";
    }
}
