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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
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
    protected void onBindViewHolder(@NonNull dogViewHolder holder, final int position, @NonNull DogRVModel model) {
        holder.dogName.setText(model.getDogName());
        holder.dogAge.setText(model.getDogAge());

        holder.dogProfileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.dogName.getContext(), Navigation.class);
                intent.putExtra("dogNAME", model.getDogName());
                intent.putExtra("dogAGE", model.getDogAge());
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
                        FirebaseDatabase.getInstance().getReference().child("Dogs")
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

        ImageButton bttn_delete;

        public dogViewHolder(@NonNull View itemView) {
            super(itemView);
            dogName = (TextView)itemView.findViewById(R.id.TVname);
            dogAge = (TextView)itemView.findViewById(R.id.TVage);

            dogProfileCard = (CardView) itemView.findViewById(R.id.card_dog);

            bttn_delete = (ImageButton) itemView.findViewById(R.id.bttn_deleteDog);
        }
    }
}
