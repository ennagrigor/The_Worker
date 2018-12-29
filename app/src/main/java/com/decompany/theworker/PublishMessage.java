package com.decompany.theworker;

import java.sql.Time;
import java.sql.Date;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Date;

import static android.app.Notification.DEFAULT_ALL;
import static com.decompany.theworker.App.channelId;

public class PublishMessage extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;
    private EditText editTextTitle;
    private EditText editTextMessage;
    private TextView notificationList;
    private String authorId;
    private String authorName;
    private FirebaseAuth mAuth;
    private DatabaseReference profileUserRef;
    private Button sentToMyTeamBtn;
    private String title;
    private String message;
    TheWorkerNotification not;
    Date date;
    Time time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_message);

        mAuth = FirebaseAuth.getInstance();
        profileUserRef = FirebaseDatabase.getInstance().getReference();

        notificationManager = NotificationManagerCompat.from(this);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextMessage = findViewById(R.id.edit_text_message);
        notificationList = (TextView)findViewById(R.id.notification_list);
        sentToMyTeamBtn = findViewById(R.id.send_to_my_team);
        authorId = mAuth.getCurrentUser().getUid();

        profileUserRef.child("workers").child(authorId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Employee e = dataSnapshot.getValue(Employee.class);
                    CurrentWorker.instance.setCurrentWorker(e);
                    authorName = e.name;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        sentToMyTeamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = editTextTitle.getText().toString();
                message = editTextMessage.getText().toString();
                Notification n = SendOnChannel(view);
                addToInbox(n);
            }
        });


    }

    public Notification SendOnChannel(View view) {

        Intent activityIntent = new Intent(this, Inbox.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);

        Intent broadcastIntent = new Intent(this, NotificationReciever.class);
        //broadcastIntent.putExtra("toastMessage", message);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(title)
                .setContentText(message)
                .setDefaults(DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .build();

        notificationManager.notify(1, notification);
        return notification;
    }

    private void addToInbox(Notification notification) {
        not = new TheWorkerNotification(authorName, editTextTitle.getText().toString(), editTextMessage.getText().toString(), 8889898);
        profileUserRef.child("notifications").child(authorId).setValue(not).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(PublishMessage.this, "Notification published", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    String message = task.getException().toString();
                    Toast.makeText(PublishMessage.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
