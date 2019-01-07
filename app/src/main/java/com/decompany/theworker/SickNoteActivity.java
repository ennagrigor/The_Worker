package com.decompany.theworker;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

public class SickNoteActivity extends AppCompatActivity {

    public String dateStart;
    public String dateEnd;
    public String URL;
    public String finalUrl;
    private static final String TAG = "SickNoteActivity";
    private TextView mDisplayDateStart;
    private TextView mDisplayDateEnd;
    private DatePickerDialog.OnDateSetListener mDateSetListenerStart;
    private DatePickerDialog.OnDateSetListener mDateSetListenerEnd;
    private Button add_file, upload, submit;
    private TextView notification;
    //private EditText mEditTextTo;
    private EditText mEditTextSubject;
    private EditText mEdittextMessage;
    Uri pdfUri;
    ProgressDialog progressDialog;



    FirebaseStorage storage; // used to upload the file
    FirebaseDatabase database; // used to store the urls of the uploaded files

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sick_note);

        //mEditTextTo = findViewById(R.id.edit_text_to);
        mEditTextSubject = findViewById(R.id.edit_text_subject);
        mEdittextMessage = findViewById(R.id.edit_text_message);
        submit = findViewById(R.id.submit);

        // the date selection option
        mDisplayDateStart = (TextView) findViewById(R.id.tvDate);
        mDisplayDateEnd = (TextView) findViewById(R.id.tvDate1);

        mDisplayDateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        SickNoteActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListenerStart,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDisplayDateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog1 = new DatePickerDialog(
                        SickNoteActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListenerEnd,
                        year,month,day);
                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog1.show();
            }
        });

        mDateSetListenerStart = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                dateStart = month + "/" + day + "/" + year;
                mDisplayDateStart.setText(dateStart);
            }
        };

        mDateSetListenerEnd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                dateEnd = month + "/" + day + "/" + year;
                mDisplayDateEnd.setText(dateEnd);
            }
        };

        // Connecting to database and storage
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();

        add_file = findViewById(R.id.add_file);
        upload = findViewById(R.id.upload);
        notification = findViewById(R.id.notification);

        add_file.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                if(ContextCompat.checkSelfPermission(SickNoteActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                    SelectPDF();
                }else{
                    ActivityCompat.requestPermissions(SickNoteActivity.this, new String [] {Manifest.permission.READ_EXTERNAL_STORAGE},1);
                }
            }
        });

        upload.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                if(pdfUri != null){
                    upLoadFile(pdfUri);
                }else{
                    Toast.makeText(SickNoteActivity.this, "Please select a file", Toast.LENGTH_SHORT).show();
                }
            }

        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });
    }

    private void upLoadFile(Uri pdfUri) {

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading file");
        progressDialog.setProgress(0);
        progressDialog.show();

        final String fileName = System.currentTimeMillis()+"";
        final StorageReference storageReference = storage.getReference(); //root
        storageReference.child("Uploads").child(fileName).putFile(pdfUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {

                        URL = taskSnapshot.getStorage().getDownloadUrl().toString();
                        final DatabaseReference reference = database.getReference();
                        reference.child(fileName).setValue(URL).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(SickNoteActivity.this, "File upload successful ", Toast.LENGTH_SHORT).show();
                                   // URL = storageReference.getDownloadUrl().toString();
                                }else{
                                    Toast.makeText(SickNoteActivity.this, "File upload not successful", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SickNoteActivity.this, "File upload not successful", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                //track the upload progress
                int currentProgress =  (int) ( 100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                URL = storageReference.getDownloadUrl().toString();

                progressDialog.setProgress(currentProgress);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult (int requestCode, @NonNull String [] permissions, @NonNull int [] grantResults){
        if(requestCode == 9 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
            SelectPDF();
        }else{
            Toast.makeText(SickNoteActivity.this, "Please provide permission", Toast.LENGTH_SHORT).show();
        }
    }

    private void SelectPDF(){

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT); // to fetch files
        startActivityForResult(intent,1);

    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        //checks if the user uploaded a file or not
        if(requestCode == 1 &&  data != null){
            pdfUri = data.getData();
            notification.setText(data.getData().getPath());
        }else{
            Toast.makeText(SickNoteActivity.this, "Please select a file", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendMail(){
        String recipientList = "daniel@decompany.com";
        String[] recipients = recipientList.split(",");

        String subject = mEditTextSubject.getText().toString();
        String message = "The Starting sick day is from " + dateStart+" and until "+ dateEnd +".\n "+ "The url for downloading the file is: "+URL+"\n" +"Additional notes: "+ mEdittextMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client: "));
    }
}

