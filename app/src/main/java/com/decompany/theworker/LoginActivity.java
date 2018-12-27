package com.decompany.theworker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    private EditText userEmail, userPassword;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        userEmail = (EditText) findViewById(R.id.input_email);
        userPassword = (EditText) findViewById(R.id.input_password);
        loginButton = (Button) findViewById(R.id.btn_login);
        loadingBar = new ProgressDialog(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllowUserToLogin();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            AllowUserToLogin();
        }
    }

    private void AllowUserToLogin() {
        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter your Email address", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
        }
        else {
            loadingBar.setTitle("Logging in...");
            loadingBar.setMessage("It may take a few seconds");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "You are now logged in", Toast.LENGTH_SHORT).show();
                        getEmployeeDetails();
                        loadingBar.dismiss();
                    }
                    else {
                        String message = task.getException().getMessage();
                        Toast.makeText(LoginActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                }
            });
        }
    }



    private DatabaseReference profileUserRef;
    private String currentUserId;

    public void getEmployeeDetails(){
        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();
        profileUserRef = FirebaseDatabase.getInstance().getReference();


        profileUserRef.child("workers").child(currentUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Employee e = dataSnapshot.getValue(Employee.class);
                    CurrentWorker.instance.setCurrentWorker(e);
                    if(e.kindOfEmployee.equals("hr")){
                         e = dataSnapshot.getValue(Hr.class);
                    }
                    else if(e.kindOfEmployee.equals("manager")){
                        e = dataSnapshot.getValue(Manager.class);
                    }
                    else if(e.kindOfEmployee.equals("employee")){
                        e = dataSnapshot.getValue(Employee.class);
                    }
                    //CurrentWorker.instance.setCurrentWorker(e);
                    //Log.e("here ", e.kindOfEmployee);
                    SendTheUserToTheRelevantActivity();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void SendTheUserToTheRelevantActivity() {
        Employee e = CurrentWorker.instance.getCurrentWorker();
        if (e.kindOfEmployee.equals("employee")) {
            Intent mainIntent = new Intent(this, MainActivity_Employee.class);
            //mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(mainIntent);
            finish();
        } else if (e.kindOfEmployee.equals("hr")) {
            Intent mainIntent = new Intent(this, MainActivity_HR.class);
            //mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(mainIntent);
            finish();
        } else if (e.kindOfEmployee.equals("manager")) {
            Intent mainIntent = new Intent(this, MainActivity_Manager.class);
            //mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(mainIntent);
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "Error! User doesn't exist", Toast.LENGTH_SHORT).show();
            loadingBar.dismiss();
        }
    }

//    final DatabaseReference db = FirebaseDatabase.getInstance().getReference();
//
//    /**
//     * register new customer
//     *
//     */
//    public void RegisterUser(final Employee employee){
//
//        db.child("workers").child(employee.uId).addListenerForSingleValueEvent(
//                new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        if(dataSnapshot.exists()){
//                        } else {
//                            db.child("workers").child(employee.uId).setValue(employee);
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                    }
//                }
//        );
//    }


}