package com.example.e_collegeapp.ui;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.e_collegeapp.Adapter.StatesAdapter;
import com.example.e_collegeapp.R;
import com.example.e_collegeapp.listener.OnRecyclerItemClickListener;
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

public class StatesActivity extends AppCompatActivity implements OnRecyclerItemClickListener {
   States states;

    FirebaseUser firebaseUser;
    FirebaseAuth auth;
    FirebaseFirestore db;

    RecyclerView recyclerView;
    ArrayList<States> statesArrayList;
    int position;
    StatesAdapter statesAdapter;

    public void initViews(){
        recyclerView = findViewById(R.id.StatesRecyclerView);
        recyclerView.setAdapter(statesAdapter);

        states = new States();

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        firebaseUser = auth.getCurrentUser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_states);
        initViews();
        fetchStatesFromCloudDb();
    }
    void fetchStatesFromCloudDb(){
        db.collection("Location")
                .document(firebaseUser.getUid()).collection("States").get()
                .addOnCompleteListener(this, new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isComplete()){
                    statesArrayList = new ArrayList<>();
                    QuerySnapshot querySnapshot = task.getResult();
                    List<DocumentSnapshot> documentSnapshots = querySnapshot.getDocuments();
                    for (DocumentSnapshot snapshot : documentSnapshots){
                        String docId = snapshot.getId();
                        States states = snapshot.toObject(States.class);
                        states.doc_id=docId;
                        statesArrayList.add(states);
                    }
                    getSupportActionBar().setTitle("Total States: " + statesArrayList.size());
                    statesAdapter= new StatesAdapter(StatesActivity.this, R.layout.list_item, statesArrayList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(StatesActivity.this);
                    recyclerView.setAdapter(statesAdapter);

                    statesAdapter.setOnRecyclerItemClickListener((OnRecyclerItemClickListener) StatesActivity.this);

                    recyclerView.setLayoutManager(linearLayoutManager);

                }else{
                    Toast.makeText(StatesActivity.this, "Some Error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
        @Override
        public void onItemClick(int position) {
            this.position = position;
            states = statesArrayList.get(position);
            Toast.makeText(this,"You Clicked on Position:"+position,Toast.LENGTH_LONG).show();
     }
}

