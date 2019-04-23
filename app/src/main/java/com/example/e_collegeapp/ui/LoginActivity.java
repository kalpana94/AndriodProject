package com.example.e_collegeapp.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_collegeapp.MainActivity;
import com.example.e_collegeapp.R;
import com.example.e_collegeapp.modal.Colleges;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText eTxtEmail, eTxtPassword;
    Button btnLogin;
    Colleges colleges;
    ProgressDialog progressDialog;
    FirebaseAuth auth;

    void initViews(){
        eTxtEmail = findViewById(R.id.editTextEmail);
        eTxtPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.buttonLogin);
        colleges = new Colleges();
        btnLogin.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");
        progressDialog.setCancelable(false);

        auth = FirebaseAuth.getInstance();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    @Override
    public void onClick(View v) {
        colleges.email = eTxtEmail.getText().toString();
        colleges.password = eTxtPassword.getText().toString();
        Toast.makeText(getApplicationContext(),"Login Clicked",Toast.LENGTH_LONG).show();
        loginUser();
    }
    void loginUser(){
        progressDialog.show();
        Toast.makeText(getApplicationContext(),"Login Clicked2",Toast.LENGTH_LONG).show();
        auth.signInWithEmailAndPassword(colleges.email,colleges.password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Log.i("test",FirebaseAuth.getInstance().getUid());
                    Toast.makeText(getApplicationContext(),FirebaseAuth.getInstance().getUid(),Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                   startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"Error "+task.getException().toString(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}


