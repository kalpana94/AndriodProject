package com.example.e_collegeapp.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_collegeapp.MainActivity;
import com.example.e_collegeapp.R;
import com.example.e_collegeapp.modal.Colleges;
import com.example.e_collegeapp.modal.Util;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CollegeActivity extends AppCompatActivity {
    EditText eTxtName, eTxtPhone, eTxtEmail;
    Button btnSubmit;
    Colleges colleges;

    boolean updateMode;

    FirebaseUser firebaseUser;
    FirebaseAuth auth;
    FirebaseFirestore db;

    void initViews() {
        eTxtName = findViewById(R.id.editTextName);
        eTxtPhone = findViewById(R.id.editTextPhone);
        eTxtEmail = findViewById(R.id.editTextEmail);
        btnSubmit = findViewById(R.id.buttonAdd);

        colleges = new Colleges();

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        firebaseUser = auth.getCurrentUser();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colleges.name = eTxtName.getText().toString();
                colleges.password = eTxtPhone.getText().toString();
                colleges.email = eTxtEmail.getText().toString();
                if (Util.isInternetConnected(CollegeActivity.this)) {
                    SaveCollegesInCloudDb();
                } else {
                    Toast.makeText(CollegeActivity.this, "Please Connect to Internet and Try Again", Toast.LENGTH_LONG).show();
                }
            }
        });
        Intent rcv = getIntent();
        updateMode = rcv.hasExtra("keyColleges");
        if (updateMode) {
            colleges = (Colleges) rcv.getSerializableExtra("keyCustomer");
            eTxtName.setText(colleges.name);
            eTxtEmail.setText(colleges.email);
            btnSubmit.setText("Update Colleges");

        }
    }

    void SaveCollegesInCloudDb() {
        if (updateMode) {
            db.collection("Colleges").document(firebaseUser.getUid())
                    .collection("users").document(colleges.docID)
                    .set(colleges)
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isComplete()) {
                                Toast.makeText(CollegeActivity.this, "Updation Finished", Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                Toast.makeText(CollegeActivity.this, "Updation Failed", Toast.LENGTH_LONG).show();
                            }
                        }

                    });
        } else {
            db.collection("Colleges").document(firebaseUser.getUid())
                    .collection("users").add(colleges)
                    .addOnCompleteListener(this, new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isComplete()) {
                                Toast.makeText(CollegeActivity.this, colleges.name + "Save college Sucessfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(CollegeActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                    });
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college);
        initViews();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 101, 1, "All Colleges");
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId ();
        if (id == 101) {
            Intent intent = new Intent(CollegeActivity.this, AllCollegeActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    void clearFields() {
        eTxtName.setText("");
        eTxtPhone.setText("");
        eTxtEmail.setText("");
    }
}

