package com.decompany.theworker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity_HR extends AppCompatActivity {
    private Button dayOffButton;
    private Button salaryRaiseButton;
    private Button sickNoteButton;
    private Button createStatisticsButton;
    private Button createNewUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hr);

        dayOffButton = (Button) findViewById(R.id.dayOff);
        salaryRaiseButton = (Button) findViewById(R.id.salaryRaise);
        sickNoteButton = (Button) findViewById(R.id.sickNote);
        //createStatisticsButton = (Button) findViewById(R.id.createStatistics);
        createNewUserButton = (Button) findViewById(R.id.createNewUser);

        dayOffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_HR.this, VacationActivity.class);
                startActivity(intent);
            }
        });

        salaryRaiseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_HR.this, SalaryRaiseActivity.class);
                startActivity(intent);
            }
        });

        sickNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_HR.this, SickNoteActivity.class);
                startActivity(intent);
            }
        });

        //createStatisticsButton.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View view) {
             //   Intent intent = new Intent(MainActivity_HR.this, StatisticsActivity.class);
            //    startActivity(intent);
         //   }
      //  });

        createNewUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_HR.this, SignupActivity.class);
                startActivity(intent);
            }
        });

    }
}
