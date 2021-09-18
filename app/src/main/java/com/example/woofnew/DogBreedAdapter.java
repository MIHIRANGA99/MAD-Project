package com.example.woofnew;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DogBreedAdapter extends RecyclerView.Adapter<DogBreedAdapter.BreedHolder>{

    List<DogBreed> data;
    int selectedItem = 0;

    public DogBreedAdapter(List<DogBreed> data){
        this.data = data;
    }
    @NonNull
    @Override
    public BreedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.breed_holder, parent, false);
        return new BreedHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull DogBreedAdapter.BreedHolder holder, int position) {
        holder.image.setImageResource(data.get(position).getImage());
        holder.title.setText(data.get(position).getName());
        holder.ratingBar.setRating(data.get(position).getRating());

        if(selectedItem == position){
            holder.cardView.animate().scaleX(1.1f);
            holder.cardView.animate().scaleY(1.1f);
            holder.title.setTextColor(Color.WHITE);
            holder.llBackground.setBackgroundResource(R.drawable.splash);
        }else{
            holder.cardView.animate().scaleX(1f);
            holder.cardView.animate().scaleY(1f);
            holder.title.setTextColor(Color.BLACK);
            holder.llBackground.setBackgroundResource(R.color.white);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BreedHolder extends RecyclerView.ViewHolder{
        RatingBar ratingBar;
        ImageView image;
        TextView title;
        LinearLayout llBackground;
        CardView cardView;
        public BreedHolder(View holder){
            super(holder);
            ratingBar = holder.findViewById(R.id.avg_rating);
            title = holder.findViewById(R.id.breed_title);
            image = holder.findViewById(R.id.breed_dpimg);
            cardView = holder.findViewById(R.id.breed_background);
            llBackground = holder.findViewById(R.id.ll_background);

            cardView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onClick(View v) {
                    selectedItem = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }
    }
}
