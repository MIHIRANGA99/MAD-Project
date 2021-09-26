package com.example.woofnew;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupHolder> {

    ArrayList<GroupsModel> mList;
    Context context;
    int selectedItem =0;

    public GroupAdapter(Context context,ArrayList<GroupsModel> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public GroupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.group_holder, parent,false);
        return new GroupHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.groupName.setText(mList.get(position).getName());
        Glide.with(context).load(mList.get(position).getImage()).into(holder.groupImage);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DogDetailsPageTwo.class);
                intent.putExtra("group_name", mList.get(position).getName());
                intent.putExtra("group_details", mList.get(position).getDetails());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class GroupHolder extends RecyclerView.ViewHolder{
        TextView groupName;
        ImageView groupImage;
        MaterialCardView cardView;

        public GroupHolder(@NonNull View holder) {
            super(holder);
            groupName = holder.findViewById(R.id.groupName);
            groupImage = holder.findViewById(R.id.groupImage);
            cardView = holder.findViewById(R.id.card_view);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedItem = getAdapterPosition();
                    //reset items, so that color changes when we click on card
                    notifyDataSetChanged();
                }
            });
        }
    }

}
