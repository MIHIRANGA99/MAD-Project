package com.example.woofnew;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class DogGroupAdapter extends RecyclerView.Adapter<DogGroupAdapter.GroupHolder> {

    List<DogGroup> data;
    Context context;
    int selectedItem = 0;

    OnGroupClick onGroupClick;

    public interface OnGroupClick {
        void onClick(int pos);
    }

    public DogGroupAdapter(List<DogGroup> data, Context context, OnGroupClick onGroupClick) {
        this.data = data;
        this.context = context;
        this.onGroupClick = onGroupClick;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public GroupHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.group_holder, parent, false);
        return new GroupHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull GroupHolder holder, int position) {
        holder.image.setImageResource(data.get(position).getImage());
        holder.title.setText(data.get(position).getName());
        if (position == selectedItem) {
            //Make card selected
            holder.cardView.setOutlineSpotShadowColor(context.getColor(R.color.dark_orange));
            holder.cardView.setOutlineAmbientShadowColor(context.getColor(R.color.dark_orange));
            holder.cardView.setStrokeWidth(2);
            holder.title.setTextColor(context.getColor(R.color.dark_orange));
            holder.image.setColorFilter(ContextCompat.getColor(context, R.color.dark_orange), PorterDuff.Mode.SRC_IN);
        } else {
            //Make card inactive
            holder.cardView.setOutlineSpotShadowColor(context.getColor(R.color.gray1));
            holder.cardView.setOutlineAmbientShadowColor(context.getColor(R.color.gray));
            holder.title.setTextColor(context.getColor(R.color.gray));
            holder.image.setColorFilter(ContextCompat.getColor(context, R.color.black), PorterDuff.Mode.SRC_IN);
            holder.cardView.setStrokeWidth(0);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class GroupHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        MaterialCardView cardView;

        public GroupHolder(View holder) {
            super(holder);
            title = holder.findViewById(R.id.txt_title);
            image = holder.findViewById(R.id.img);
            cardView = holder.findViewById(R.id.card_view);

            cardView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onClick(View view) {
                    selectedItem = getAdapterPosition();
                    //reset items, so that color changes when we click on card
                    if (onGroupClick != null) {
                        onGroupClick.onClick(getAdapterPosition());
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }
}

