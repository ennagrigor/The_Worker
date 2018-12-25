package com.decompany.theworker;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private EditText nameText;
    private EditText emailText;
    private EditText passwordText;
    private EditText reEnterPasswordText;
    private EditText departmentText;
    private EditText linkToPhotoText;
    private EditText mobileText;
    private EditText managerNameText;
    private EditText positionText;
    private EditText kindOfEmployeeText;
    private EditText salaryText;

    private Button signupButton;

    private FirebaseAuth mAuth;
    private String currentUserId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        nameText = findViewById(R.id.input_name);
        emailText = findViewById(R.id.input_email);
        passwordText = findViewById(R.id.input_password);
        reEnterPasswordText = findViewById(R.id.input_reEnterPassword);
        departmentText = findViewById(R.id.input_department);
        linkToPhotoText = findViewById(R.id.link_to_photo);
        mobileText = findViewById(R.id.input_mobile);
        managerNameText = findViewById(R.id.manager_name);
        positionText = findViewById(R.id.input_position);
        kindOfEmployeeText = findViewById(R.id.input_kind_of_employee);
        salaryText = findViewById(R.id.input_salary);
        signupButton = findViewById(R.id.btn_signup);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AllowUserToSignup();

                final String name = nameText.getText().toString();
                final String position = positionText.getText().toString();
                final String email = emailText.getText().toString();
                final String password = passwordText.getText().toString();
                final String reEnterPassword = reEnterPasswordText.getText().toString();
                final String mobile = mobileText.getText().toString();
                final String managerName = managerNameText.getText().toString();
                final String department = departmentText.getText().toString();
                final String linkToPhoto = linkToPhotoText.getText().toString();
                final String kindOfEmployee = kindOfEmployeeText.getText().toString();
                final String salary = salaryText.getText().toString();

                validate(name, position, email, password, reEnterPassword, mobile, managerName, department, linkToPhoto, kindOfEmployee, salary);

                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        currentUserId = mAuth.getCurrentUser().getUid();
                                        final Employee e = new Employee(currentUserId, name, position, email, mobile, managerName, department, linkToPhoto, password, kindOfEmployee, salary);
                                        FirebaseDatabase.getInstance().getReference().child("workers").child(currentUserId).setValue(e).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                Toast.makeText(SignupActivity.this, "New user created", Toast.LENGTH_SHORT).show();
                                                                finish();
                                                            } else {
                                                                String message = task.getException().toString();
                                                                Toast.makeText(SignupActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });
                                                }

                                            }});}});}

                    public void onSignupSuccess () {
                        signupButton.setEnabled(true);
                        setResult(RESULT_OK, null);
                        finish();
                    }

                    public void onSignupFailed () {
                        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

                        signupButton.setEnabled(true);
                    }
                    public boolean validate (String name, String position, String email, String password, String reEnterPassword, String mobile, String managerName, String department, String linkToPhoto, String kindOfEmployee, String salary ){
                        boolean valid = true;

                        if (name.isEmpty() || name.length() < 3) {
                            nameText.setError("at least 3 characters");
                            valid = false;
                        } else {
                            nameText.setError(null);
                        }

                        if (department.isEmpty()) {
                            departmentText.setError("Enter Valid Department");
                            valid = false;
                        } else {
                            departmentText.setError(null);
                        }

                        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            emailText.setError("enter a valid email address");
                            valid = false;
                        } else {
                            emailText.setError(null);
                        }

                        if (mobile.isEmpty() || mobile.length() != 10) {
                            mobileText.setError("Enter Valid Mobile Number");
                            valid = false;
                        } else {
                            mobileText.setError(null);
                        }

                        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
                            passwordText.setError("between 4 and 10 alphanumeric characters");
                            valid = false;
                        } else {
                            passwordText.setError(null);
                        }

                        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
                            reEnterPasswordText.setError("Password Do not match");
                            valid = false;
                        } else {
                            reEnterPasswordText.setError(null);
                        }

                        return valid;
                    }
                }
