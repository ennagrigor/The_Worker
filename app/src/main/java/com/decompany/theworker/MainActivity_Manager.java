package com.decompany.theworker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity_Manager extends AppCompatActivity {

    private Button dayOffButton;
    private Button salaryRaiseButton;
    private Button sickNoteButton;
    private Button publishAmessageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_manager);

        publishAmessageBtn =findViewById(R.id.publishAMessage);
        dayOffButton = (Button) findViewById(R.id.dayOff);
        salaryRaiseButton = (Button) findViewById(R.id.salaryRaise);
        sickNoteButton = (Button) findViewById(R.id.sickNote);

        dayOffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_Manager.this, VacationActivity.class);
                startActivity(intent);
            }
        });

        salaryRaiseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_Manager.this, SalaryRaiseActivity.class);
                startActivity(intent);
            }
        });

        sickNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_Manager.this, SickNoteActivity.class);
                startActivity(intent);
            }
        });

        publishAmessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_Manager.this, NotificationActivity.class);
                startActivity(intent);
            }
        });
    }
}
