package com.decompany.theworker;

import java.util.Date;

public class TheWorkerNotification {
    private String notificationAuthor;
    private String notificationTitle;
    private String notificationDescription;
    private String notificationDate;

    public TheWorkerNotification(String notificationAuthor, String notificationTitle, String notificationDescription, String notificationDate) {
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

    public String getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(String notificationDate) {
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
