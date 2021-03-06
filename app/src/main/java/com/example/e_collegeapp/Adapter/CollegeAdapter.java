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
import com.example.e_collegeapp.modal.Colleges;

import java.util.ArrayList;

public class CollegeAdapter extends RecyclerView.Adapter<CollegeAdapter.ViewHolder>  {
    Context context;
    int resources;
    ArrayList<Colleges> objects;

    OnRecyclerItemClickListener recyclerItemClickListener;

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener recyclerItemClickListener) {
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    public CollegeAdapter(Context context, int resources, ArrayList<Colleges> objects) {
        this.context = context;
        this.resources = resources;
        this.objects = objects;
    }


    @NonNull
    @Override
    public CollegeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(resources, parent, false);
        final CollegeAdapter.ViewHolder holder = new CollegeAdapter.ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(holder.getAdapterPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Colleges colleges = objects.get(i);
        viewHolder.txtTitle.setText(colleges.name);
        viewHolder.txtCity.setText(colleges.city);
        viewHolder.txtState.setText(colleges.state);
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        TextView txtCity;
        TextView txtState;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.textViewTitle);
            txtCity = itemView.findViewById(R.id.textViewCity);
            txtState = itemView.findViewById(R.id.textViewState);

        }
    }

}
