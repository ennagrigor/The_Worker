package com.decompany.theworker;

import java.sql.Time;
import java.sql.Date;

public class TheWorkerNotification {
    private String notificationAuthor;
    private String notificationTitle;
    private String notificationDescription;
    private long notificationDate;
    //private Time notificationHour;

    public TheWorkerNotification(String notificationAuthor, String notificationTitle, String notificationDescription, long notificationDate) {
        this.notificationAuthor = notificationAuthor;
        this.notificationTitle = notificationTitle;
        this.notificationDescription = notificationDescription;
        this.notificationDate = notificationDate;
        //this.notificationHour = notificationHour;
    }

    public TheWorkerNotification() {}

    public String getNotificationAuthor() {
        return notificationAuthor;
    }

    public void setNotificationAuthor(String notificationAuthor) {
        this.notificationAuthor = notificationAuthor;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationDescription() {
        return notificationDescription;
    }

    public void setNotificationDescription(String notificationDescription) {
        this.notificationDescription = notificationDescription;
    }

    public long getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(long notificationDate) {
        this.notificationDate = notificationDate;
    }

//    public Time getNotificationHour() {
//        return notificationHour;
//    }
//
//    public void setNotificationHour(Time notificationHour) {
//        this.notificationHour = notificationHour;
//    }
}
