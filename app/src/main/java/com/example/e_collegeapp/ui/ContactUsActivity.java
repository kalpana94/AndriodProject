package com.example.e_collegeapp.ui;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_collegeapp.R;
import com.example.e_collegeapp.modal.Contact;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class ContactUsActivity extends AppCompatActivity {
    EditText etxtaddress,etxtwebsite,etxtemail,etxtphone;
    Button btnsave;
    Contact contact;

    FirebaseUser firebaseUser;
    FirebaseAuth auth;
    FirebaseFirestore db;

    ProgressDialog progressDialog;

    void initViews(){
        etxtaddress = findViewById(R.id.editTextAddress);
        etxtwebsite=findViewById(R.id.editTextWebsite);
        etxtemail=findViewById(R.id.editTextemail);
        etxtphone=findViewById(R.id.editTextphone);
        btnsave = findViewById(R.id.buttonSave);
        contact = new Contact();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");
        progressDialog.setCancelable(false);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        firebaseUser = auth.getCurrentUser();

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            contact.address=etxtaddress.getText().toString();
            contact.website=etxtwebsite.getText().toString();
            contact.email=etxtemail.getText().toString();
            contact.phone=etxtphone.getText().toString();
            savedetails();

            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        initViews();

    }
    void savedetails(){
        progressDialog.dismiss();
        Toast.makeText(getApplicationContext(),"Save information",Toast.LENGTH_LONG).show();
        progressDialog.dismiss();
    }
}
