package com.example.jpatry.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int notificationID;
    private String notificationGroupID;
    private String notificationContent;
    private String notificationPublisherID;
    private Timestamp notificationDate;

    public Timestamp getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Timestamp notificationDate) {
        this.notificationDate = notificationDate;
    }

    public int getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
    }

    public String getNotificationGroupID() {
        return notificationGroupID;
    }

    public void setNotificationGroupID(String notificationGroupID) {
        this.notificationGroupID = notificationGroupID;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }



    public String getNotificationPublisherID() {
        return notificationPublisherID;
    }

    public void setNotificationPublisherID(String notificationPublisherID) {
        this.notificationPublisherID = notificationPublisherID;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationID=" + notificationID +
                ", notificationGroupID='" + notificationGroupID + '\'' +
                ", notificationContent='" + notificationContent + '\'' +
                ", notificationPublisherID='" + notificationPublisherID + '\'' +
                '}';
    }
}
