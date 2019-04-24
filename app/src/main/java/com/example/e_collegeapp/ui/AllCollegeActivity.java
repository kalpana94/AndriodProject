package com.example.e_collegeapp.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.e_collegeapp.Adapter.CollegeAdapter;
import com.example.e_collegeapp.Adapter.CoursesAdapter;
import com.example.e_collegeapp.R;
import com.example.e_collegeapp.listener.OnRecyclerItemClickListener;
import com.example.e_collegeapp.modal.Colleges;
import com.example.e_collegeapp.modal.Courses;
import com.example.e_collegeapp.modal.Util;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

import io.opencensus.stats.View;

public class AllCollegeActivity extends AppCompatActivity  implements OnRecyclerItemClickListener{
    RecyclerView recyclerView;
    ArrayList<Colleges> colleges;
    int position;
    CollegeAdapter collegeAdapter;
    Colleges college;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore db;

    void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(collegeAdapter);


        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        college = new Colleges();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_college);
        initViews();
        if(Util.isInternetConnected(this)) {
            fetchCollegesFromCloudDb();
        }else{
            Toast.makeText(AllCollegeActivity.this,"Please Connect to Internet and Try Again",Toast.LENGTH_LONG).show();
        }
        fetchCollegesFromCloudDb();

    }

    void fetchCollegesFromCloudDb() {
        db.collection("Colleges")
                .document(firebaseUser.getUid()).collection("user").get()
                .addOnCompleteListener(this, new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isComplete()) {
                            colleges = new ArrayList<>();
                            QuerySnapshot querySnapshot = task.getResult();

                            List<DocumentSnapshot> documentSnapshots = querySnapshot.getDocuments();
                            for (DocumentSnapshot snapshot : documentSnapshots) {
                                String docId = snapshot.getId();
                                Colleges college = snapshot.toObject(Colleges.class);
                                college.docID = docId;
                                colleges.add(college);
                                //       Courses courses = Snapshot.toObject(Colleges.class);
                                Log.i("size", Integer.toString(colleges.size()));

                            }
                            getSupportActionBar().setTitle("Total Colleges: " + colleges.size());
                            collegeAdapter = new CollegeAdapter(AllCollegeActivity.this, R.layout.list_item, colleges);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AllCollegeActivity.this);
                            recyclerView.setAdapter(collegeAdapter);
                            collegeAdapter.setOnRecyclerItemClickListener((OnRecyclerItemClickListener) AllCollegeActivity.this);

                            recyclerView.setLayoutManager(linearLayoutManager);

                        } else {
                            Toast.makeText(AllCollegeActivity.this, "Some Error", Toast.LENGTH_LONG).show();
                        }
                    }

                });
    }
    void showCollegeDetails() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(college.name + "Details:");
        builder.setMessage(college.toString());
        builder.setPositiveButton("Done", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    void deleteCollegesFromCloudDB(){
        db.collection("colleges").document(firebaseUser.getUid())
                .collection("users").document(college.docID)
                .delete()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isComplete()){
                            Toast.makeText(AllCollegeActivity.this,"Deletion Finished",Toast.LENGTH_LONG).show();
                            colleges.remove(position);

                          //  CollegeAdapter.notifyDataSetChanged();; // Refresh Your RecyclerView
                        }else{
                            Toast.makeText(AllCollegeActivity.this,"Deletion Failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    void askForDeletion(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete "+college.name);
        builder.setMessage("Are You Sure ?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteCollegesFromCloudDB();
            }
        });
        builder.setNegativeButton("Cancel",null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    void showOptions() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] items = {"View " + college.name, "Update " + college.name, "Delete " + college.name, "Cancel"};
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        showCollegeDetails();
                        break;

                    case 1:
                        Intent intent = new Intent(AllCollegeActivity.this, CollegeActivity.class);
                        intent.putExtra("keyColleges", colleges);
                        startActivity(intent);
                        break;

                    case 2:
                        askForDeletion();
                        break;
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
    @Override
    public void onItemClick(int position) {
        this.position = position;
        college = colleges.get(position);
        Toast.makeText(this,"You Clicked on Position:"+position,Toast.LENGTH_LONG).show();
        showCollegeDetails();
        Intent intent = new Intent(AllCollegeActivity.this,AddCoursesActivity.class);
        startActivity(intent);
        finish();
        showOptions();
    }

}


