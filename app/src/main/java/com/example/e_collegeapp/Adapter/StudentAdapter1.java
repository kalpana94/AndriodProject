package com.example.e_collegeapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.e_collegeapp.R;
import com.example.e_collegeapp.listener.OnRecyclerItemClickListener;
import com.example.e_collegeapp.modal.Student1;

import java.util.ArrayList;

public class StudentAdapter1 extends RecyclerView.Adapter<StudentAdapter1.ViewHolder> {
    Context context;
    int resources;
    ArrayList<Student1> objects;

    OnRecyclerItemClickListener onRecyclerItemClickListener;

    public StudentAdapter1(Context context, int resources,ArrayList<Student1> objects){
        this.context = context;
        this.resources = resources;
        this.objects = objects;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(resources, parent, false);
        final StudentAdapter1.ViewHolder holder=new StudentAdapter1.ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecyclerItemClickListener.onItemClick(holder.getAdapterPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     Student1 student1 = objects.get(position);
        holder.txtName.setText(student1.Name);
        holder.txtEmail.setText(student1.Email);
    }

    @Override
    public int getItemCount() {
          return objects.size();
    }
    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName,txtEmail;
        public ViewHolder(View Itemview) {
            super(Itemview);
            txtName=itemView.findViewById(R.id.Name);
            txtEmail=itemView.findViewById(R.id.Email);
        }
    }
}
