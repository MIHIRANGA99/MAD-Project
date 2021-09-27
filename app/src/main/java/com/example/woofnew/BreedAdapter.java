package com.example.woofnew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class BreedAdapter extends RecyclerView.Adapter<BreedAdapter.BreedHolder> {

    ArrayList<BreedsModel> mList;
    Context context;

    public BreedAdapter(Context context,ArrayList<BreedsModel> mList) {
        this.context = context;
        this.mList = mList;
    }


    @NonNull
    @Override
    public BreedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.breed_holder, parent,false);
        return new BreedHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BreedHolder holder, int position) {
        holder.breedName.setText(mList.get(position).getName());
        holder.breedDetails.setText(mList.get(position).getDetails());
        Glide.with(context).load(mList.get(position).getImage()).into(holder.breedImage);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class BreedHolder extends RecyclerView.ViewHolder{

        TextView breedName;
        TextView breedDetails;
        ImageView breedImage;

        public BreedHolder(@NonNull View holder) {
            super(holder);

            breedName = holder.findViewById(R.id.breed_name);
            breedImage = holder.findViewById(R.id.breed_image);
            breedDetails = holder.findViewById(R.id.breed_details);
        }
    }

}