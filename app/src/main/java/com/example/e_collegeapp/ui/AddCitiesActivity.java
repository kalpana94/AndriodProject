package com.example.e_collegeapp.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_collegeapp.R;
import com.example.e_collegeapp.modal.Cities;
import com.example.e_collegeapp.modal.States;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddCitiesActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etxtcities;
    Button btnsubmit, btnView;

    Cities cities;

    FirebaseUser firebaseUser;
    FirebaseAuth auth;
    FirebaseFirestore db;
     void initViews() {
         etxtcities = findViewById(R.id.editTextcitiesname);
         btnsubmit = findViewById(R.id.buttonsubmit);
         btnView = findViewById(R.id.buttonview);
         cities = new Cities();
         auth = FirebaseAuth.getInstance();
         db = FirebaseFirestore.getInstance();
         firebaseUser = auth.getCurrentUser();

         btnsubmit.setOnClickListener(this);
         btnView.setOnClickListener(this);

     }
    void saveCitiesCloudInDb() {
        db.collection("Location").document(firebaseUser.getUid())
                .collection("Cities").add(cities)
                .addOnCompleteListener(this, new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isComplete()) {
                            Toast.makeText(AddCitiesActivity.this, "Cities Saved in DB", Toast.LENGTH_LONG).show();
                            clearFields();
                        }
                    }
                });

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cities);
        initViews();
    }
    void clearFields() {
        etxtcities.setText("");

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.buttonsubmit){
            cities.Name = etxtcities.getText().toString();
            saveCitiesCloudInDb();
        }else{
            Intent intent = new Intent(AddCitiesActivity.this, CitiesActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
