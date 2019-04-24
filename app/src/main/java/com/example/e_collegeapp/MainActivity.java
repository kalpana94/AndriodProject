package com.example.e_collegeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.e_collegeapp.ui.AdmissionActivity;
import com.example.e_collegeapp.ui.AllCollegeActivity;
import com.example.e_collegeapp.ui.CollegeActivity;
import com.example.e_collegeapp.ui.FeesStructureActivity;
import com.example.e_collegeapp.ui.SplashActivity;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    Button btnaddclg,btnviewclg,btnviewstd,btnviewpaymnt;

    void initViews(){
        btnaddclg = findViewById(R.id.buttonaddcollege);
        btnviewclg=findViewById(R.id.buttonviewcolleges);
        btnviewstd = findViewById(R.id.buttonViewstudent);
        btnviewpaymnt=findViewById(R.id.buttonviewPayment);

        btnaddclg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, CollegeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnviewclg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,AllCollegeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        initViews();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,101,0,"LogOut");
        menu.add(2,102,0,"All Colleges");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==101){
            auth.signOut();
            Intent intent = new Intent(MainActivity.this, SplashActivity.class);
            startActivity(intent);
            finish();

        }else{
            Intent intent = new Intent(MainActivity.this, AllCollegeActivity.class);
            startActivity(intent);
            finish();

        }
        return super.onOptionsItemSelected(item);
    }
}
