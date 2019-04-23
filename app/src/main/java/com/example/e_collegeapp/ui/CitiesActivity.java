package com.example.e_collegeapp.ui;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.e_collegeapp.Adapter.CitiesAdapter;
import com.example.e_collegeapp.Adapter.StatesAdapter;
import com.example.e_collegeapp.R;
import com.example.e_collegeapp.listener.OnRecyclerItemClickListener;
import com.example.e_collegeapp.modal.Cities;
import com.example.e_collegeapp.modal.States;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CitiesActivity extends AppCompatActivity implements OnRecyclerItemClickListener {
    Cities cities;

    FirebaseUser firebaseUser;
    FirebaseAuth auth;
    FirebaseFirestore db;

    RecyclerView recyclerView;
    ArrayList<Cities> citiesArrayList;
    CitiesAdapter citiesAdapter;
    int position;

    void initViews(){
        recyclerView = findViewById(R.id.citiesrecyclerView);
        recyclerView.setAdapter(citiesAdapter);

        cities = new Cities();
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        firebaseUser = auth.getCurrentUser();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        initViews();
    }
    void fetchCitiesFromCloudDb(){
        db.collection("Location").document(firebaseUser.getUid())
                .collection("Cities").get()
                .addOnCompleteListener(this, new OnCompleteListener<QuerySnapshot>(){

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isComplete()){
                            citiesArrayList = new ArrayList<>();
                            QuerySnapshot querySnapshot = task.getResult();
                            List<DocumentSnapshot> documentSnapshots = querySnapshot.getDocuments();
                            for (DocumentSnapshot snapshot : documentSnapshots){
                                String docId = snapshot.getId();
                                Cities cities = snapshot.toObject(Cities.class);
                                cities.doc_id=docId;
                                citiesArrayList.add(cities);
                            }
                            getSupportActionBar().setTitle("Total Cities: " + citiesArrayList.size());
                            citiesAdapter= new CitiesAdapter(CitiesActivity.this, R.layout.list_item, citiesArrayList);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CitiesActivity.this);
                            recyclerView.setAdapter(citiesAdapter);

                            citiesAdapter.setOnRecyclerItemClickListener((OnRecyclerItemClickListener) CitiesActivity.this);

                            recyclerView.setLayoutManager(linearLayoutManager);
                        }else{
                            Toast.makeText(CitiesActivity.this, "Some Error", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    @Override
    public void onItemClick(int position) {
        this.position = position;
        cities = citiesArrayList.get(position);
        Toast.makeText(this,"You Clicked on Position:"+position,Toast.LENGTH_LONG).show();
    }
}
