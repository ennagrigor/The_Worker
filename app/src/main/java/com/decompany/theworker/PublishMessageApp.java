package com.decompany.theworker;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class PublishMessageApp extends Application {

    public static final String channelId = "My Team";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate();
        //setContentView(R.layout.activity_publish_message);
        createNotificationChannel();
    }



    private void createNotificationChannel() {
        Manager m = new Manager();
        m = (Manager) CurrentWorker.instance.getCurrentWorker();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(
                    channelId, "Team A", NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("Messages for team A");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }
    }
}
