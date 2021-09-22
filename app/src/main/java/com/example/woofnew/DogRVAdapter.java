package com.example.woofnew;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DogRVAdapter extends FirebaseRecyclerAdapter<DogRVModel,DogRVAdapter.dogViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public DogRVAdapter(@NonNull FirebaseRecyclerOptions<DogRVModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull dogViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull DogRVModel model) {
        holder.dogName.setText(model.getDogName());
        holder.dogAge.setText(model.getDogBreed());

        Glide.with(holder.dogName.getContext()).load(model.getImageURL()).into(holder.profilePic);

        holder.dogProfileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.dogName.getContext(), Navigation.class);
                intent.putExtra("dogNAME", model.getDogName());
                intent.putExtra("dogAGE", model.getDogAge());
                intent.putExtra("dogGENDER", model.getDogGender());
                intent.putExtra("dogBREED", model.getDogBreed());
                intent.putExtra("dogWEIGHT", model.getDogWeight());
                intent.putExtra("dogID", model.getDogID());
                intent.putExtra("proImgURL", model.getImageURL());
                holder.dogName.getContext().startActivity(intent);
            }
        });

        holder.bttn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.dogName.getContext());
                builder.setTitle("Are You Sure To Delete " + model.getDogName() + "'s Profile?");
                builder.setMessage("This will remove " + model.getDogName() + "'s profile permanently");

                builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .child("Dogs")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.dogName.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public dogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_rv_item,parent,false);
        return new dogViewHolder(view);
    }

    class dogViewHolder extends RecyclerView.ViewHolder{
        Intent intent;

        TextView dogName, dogAge;
        CardView dogProfileCard;

        ShapeableImageView profilePic;

        ImageButton bttn_delete;


        public dogViewHolder(@NonNull View itemView) {
            super(itemView);
            dogName = (TextView)itemView.findViewById(R.id.TVname);
            dogAge = (TextView)itemView.findViewById(R.id.TVage);

            profilePic = (ShapeableImageView)itemView.findViewById(R.id.profilePic_card_img);

            dogProfileCard = (CardView) itemView.findViewById(R.id.card_dog);

            bttn_delete = (ImageButton) itemView.findViewById(R.id.bttn_deleteDog);
        }
    }
}
