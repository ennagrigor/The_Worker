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
      //  createStatisticsButton = (Button) findViewById(R.id.createStatistics);
        createNewUserButton = (Button) findViewById(R.id.createNewUser);

        dayOffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VacationActivity.class);
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

        createStatisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_HR.this, StatisticsActivity.class);
                startActivity(intent);
            }
        });

        createNewUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_HR.this, SignupActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void kkk(View view){
        Intent intent = new Intent(this, VacationActivity.class);
        startActivity(intent);
    }
}
