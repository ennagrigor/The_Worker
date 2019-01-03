package com.decompany.theworker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.google.firebase.database.FirebaseDatabase.*;


//Employee login

public class MainActivity_Employee extends AppCompatActivity {

    private Button dayOffButton;
    private Button salaryRaiseButton;
    private Button sickNoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_employee);

        dayOffButton = (Button) findViewById(R.id.dayOff);
        salaryRaiseButton = (Button) findViewById(R.id.salaryRaise);
        sickNoteButton = (Button) findViewById(R.id.sickNote);

        dayOffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_Employee.this, VacationActivity.class);
                startActivity(intent);
            }
        });

        salaryRaiseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_Employee.this, SalaryRaiseActivity.class);
                startActivity(intent);
            }
        });

        sickNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_Employee.this, SickNoteActivity.class);
                startActivity(intent);
            }
        });


    }
}
