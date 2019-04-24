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
import com.example.e_collegeapp.modal.Cities;
import com.example.e_collegeapp.modal.States;

import java.util.ArrayList;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ViewHolder>  {

    Context context;
    int resources;
    ArrayList<Cities> objects;

    OnRecyclerItemClickListener recyclerItemClickListener;
    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener recyclerItemClickListener) {
        this.recyclerItemClickListener = recyclerItemClickListener;
    }
    public  CitiesAdapter(Context context, int resources, ArrayList<Cities> objects) {
        this.context = context;
        this.resources = resources;
        this.objects = objects;
    }


    @NonNull
    @Override
    public CitiesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(resources, parent, false);
        final CitiesAdapter.ViewHolder holder = new CitiesAdapter.ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(holder.getAdapterPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CitiesAdapter.ViewHolder viewHolder, int i) {
        Cities cities= objects.get(i);
        viewHolder.txtTitle.setText(cities.Name);
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.textViewTitle);
        }


    }
}
