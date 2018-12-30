package com.decompany.theworker;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

public class App extends Application {

    private String team;
    public static final String channelA = "Team A";
    //You can add more channels here

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate();
        //setContentView(R.layout.activity_publish_message);
        createNotificationChannel(channelA);
    }

    private void createNotificationChannel(String channelID) {
        Manager m;
        m = (Manager) CurrentWorker.instance.getCurrentWorker();
        team = m.team;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channelNotification = new NotificationChannel(
                    App.channelA, "Team " + team, NotificationManager.IMPORTANCE_HIGH
            );
            channelNotification.setDescription("Messages for team " + team);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channelNotification);
        }
    }
}
