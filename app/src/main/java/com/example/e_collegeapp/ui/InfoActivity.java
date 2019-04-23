package com.example.e_collegeapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.e_collegeapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class InfoActivity extends AppCompatActivity {
    TextView collegeInfo, deadline,fees,contact_us;
    EditText etxtinf;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore db;

    void initViews(){
        deadline = findViewById(R.id.textViewDeadline);
        fees = findViewById(R.id.textViewFees);
        collegeInfo = findViewById(R.id.textViewcollege_info);
        contact_us = findViewById(R.id.textViewContact_us);
        etxtinf=findViewById(R.id.editTextinfo);
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        deadline.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent= new Intent(InfoActivity.this,DeadlineActivity.class);
        startActivity(intent);
        finish();
       }
    });

        fees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(InfoActivity.this,feesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        contact_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(InfoActivity.this,ContactUsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        collegeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(InfoActivity.this,collegeInfoActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        initViews();
    }

}
