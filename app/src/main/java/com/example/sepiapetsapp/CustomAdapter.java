package com.example.sepiapetsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;


import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    ArrayList<String> petImages;
    ArrayList<String> petNames;

    Context context;
    OnItemClockListener mListener;

    public interface OnItemClockListener {
        void onItemClick(int position);
    }

    public void setOnItemClockListener(OnItemClockListener listener){
        mListener = listener;
    }

    public CustomAdapter(ArrayList<String> petImages, ArrayList<String> petNames, Context context) {
        this.petImages = petImages;
        this.petNames = petNames;
        this.context = context;
    }

    public ArrayList<String> getPetImages() {
        return petImages;
    }

    public ArrayList<String> getPetNames() {
        return petNames;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent,false);
        MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Glide.with(context)
                .load(petImages.get(position))
                .fitCenter()
                .into(holder.image);



        holder.name.setText(petNames.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null){
                    //int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        mListener.onItemClick(position);
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return petNames.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView image;


        public  MyViewHolder(View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.image);



        }
    }
}

