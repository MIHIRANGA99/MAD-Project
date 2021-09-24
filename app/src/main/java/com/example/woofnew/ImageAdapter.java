package com.example.woofnew;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.database.core.Context;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ImageAdapter extends FirebaseRecyclerAdapter<Uploadpics,ImageAdapter.imageViewHolder> {

    public ImageAdapter(@NonNull FirebaseRecyclerOptions<Uploadpics> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull imageViewHolder holder, int position, @NonNull Uploadpics model) {
        holder.titleView.setText(model.getmName());
        Picasso.get().load(model.getmImageURL())
                .resize(300,300)
                .centerCrop()
                .into(holder.dbImageView);

    }

    @NonNull
    @Override
    public imageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_item, parent, false);
        return new imageViewHolder(view);
    }

    public class imageViewHolder extends RecyclerView.ViewHolder{

        ShapeableImageView dbImageView;
        TextView titleView;

        public imageViewHolder(@NonNull View itemView) {
            super(itemView);

            dbImageView = (ShapeableImageView) itemView.findViewById(R.id.db_imageSIV);
            titleView = itemView.findViewById(R.id.db_TitleTV);
        }
    }

}
