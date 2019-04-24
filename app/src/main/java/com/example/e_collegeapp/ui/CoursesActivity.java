package com.example.e_collegeapp.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_collegeapp.Adapter.CollegeAdapter;
import com.example.e_collegeapp.Adapter.CoursesAdapter;
import com.example.e_collegeapp.R;
import com.example.e_collegeapp.listener.OnRecyclerItemClickListener;
import com.example.e_collegeapp.modal.Colleges;
import com.example.e_collegeapp.modal.Courses;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CoursesActivity extends AppCompatActivity implements OnRecyclerItemClickListener{

    Courses courses;

    FirebaseUser firebaseUser;
    FirebaseAuth auth;
    FirebaseFirestore db;

    RecyclerView recyclerView;
    ArrayList<Courses>coursesArrayList;
    int position;
    CoursesAdapter coursesAdapter;

    public void initViews(){
        recyclerView = findViewById(R.id.CoursesRecyclerView);
        recyclerView.setAdapter(coursesAdapter);


        courses = new Courses();

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        firebaseUser = auth.getCurrentUser();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        initViews();
        Toast.makeText(CoursesActivity.this,"Some error",Toast.LENGTH_LONG).show();
        fetchCoursesFromCloudDb();
    }
    void fetchCoursesFromCloudDb() {

      Toast.makeText(CoursesActivity.this,"Uid is there",Toast.LENGTH_LONG).show();
        db.collection("Colleges")
                    .document(firebaseUser.getUid()).collection("Courses").get()
                .addOnCompleteListener(this, new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isComplete()) {
                            coursesArrayList = new ArrayList<>();
                            QuerySnapshot querySnapshot = task.getResult();

                            List<DocumentSnapshot> documentSnapshots = querySnapshot.getDocuments();
                            for (DocumentSnapshot snapshot : documentSnapshots) {
                                String docId = snapshot.getId();
                                Courses courses = snapshot.toObject(Courses.class);
                                courses.doc_Id = docId;
                                coursesArrayList.add(courses);
                                //       Courses courses = Snapshot.toObject(Colleges.class);
                                //Log.i("size", Integer.toString(courses.size()));

                            }
                            getSupportActionBar().setTitle("Total Colleges: " + coursesArrayList.size());
                            coursesAdapter = new CoursesAdapter(CoursesActivity.this, R.layout.list_item, coursesArrayList);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CoursesActivity.this);
                            recyclerView.setAdapter(coursesAdapter);

                            coursesAdapter.setOnRecyclerItemClickListener((OnRecyclerItemClickListener) CoursesActivity.this);

                            recyclerView.setLayoutManager(linearLayoutManager);

                        } else {
                            Toast.makeText(CoursesActivity.this, "Some Error", Toast.LENGTH_LONG).show();
                        }
                    }

                });
    }

    @Override
    public void onItemClick(int position) {
        this.position = position;
        courses = coursesArrayList.get(position);
        Toast.makeText(this,"You Clicked on Position:"+position,Toast.LENGTH_LONG).show();
        // showCollegeDetails();
    }
}
