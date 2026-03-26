package com.subscription.model;
import java.sql.Date;

public class Subscription {
    private int id;
    private int userId;
    private int serviceId;
    private Date startDate;
    private Date endDate;
    private String status;

    public Subscription(int userId, int serviceId, Date startDate, Date endDate, String status) {
        this.userId = userId;
        this.serviceId = serviceId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    // Constructor with id (for fetch from DB)
    public Subscription(int id, int userId, int serviceId, Date startDate, Date endDate, String status) {
        this.id = id;
        this.userId = userId;
        this.serviceId = serviceId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", userId=" + userId +
                ", serviceId=" + serviceId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                '}';
    }

}
