package com.example.woofnew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VetAdapter extends RecyclerView.Adapter<VetAdapter.VetViewHolder> {

    Context context;

    ArrayList<User> list;

    public VetAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new VetViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VetViewHolder holder, int position) {

        User user = list.get(position);
        holder.VetName.setText(user.getvetName());
        holder.VetAddress.setText(user.getvetAddress());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class VetViewHolder extends  RecyclerView.ViewHolder{

        TextView VetName, VetAddress;


        public VetViewHolder(@NonNull View itemView) {
            super(itemView);

            VetName = itemView.findViewById(R.id.tvName_vet);
            VetAddress = itemView.findViewById(R.id.tvAddress_vet);
        }
    }
}
