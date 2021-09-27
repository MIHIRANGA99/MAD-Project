package com.example.woofnew;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
    public void onBindViewHolder(@NonNull VetViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        User user = list.get(position);
        holder.VetName.setText("Dr."+user.getvetName());
        holder.VetAddress.setText(user.getvetAddress());


        holder.vet_CV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, VetProfile.class);
                i.putExtra("vet_name", list.get(position).getvetName());
                i.putExtra("vet_address", list.get(position).getvetAddress());
                i.putExtra("vet_contact", list.get(position).getVetContact());
                i.putExtra("vet_email", list.get(position).getVetEmail());

                view.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class VetViewHolder extends  RecyclerView.ViewHolder{

        TextView VetName, VetAddress;
        CardView vet_CV;


        public VetViewHolder(@NonNull View itemView) {
            super(itemView);

            VetName = itemView.findViewById(R.id.tvName_vet);
            VetAddress = itemView.findViewById(R.id.tvAddress_vet);
            vet_CV = itemView.findViewById(R.id.CV_vet);
        }
    }
}
