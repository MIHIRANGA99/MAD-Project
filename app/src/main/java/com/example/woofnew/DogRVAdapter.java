package com.example.woofnew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

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
    protected void onBindViewHolder(@NonNull dogViewHolder holder, int position, @NonNull DogRVModel model) {
        holder.dogName.setText("Dog Name: " + model.getDogName());
        holder.dogAge.setText(model.getDogAge() + " yrs");
    }

    @NonNull
    @Override
    public dogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_rv_item,parent,false);
        return new dogViewHolder(view);
    }

    class dogViewHolder extends RecyclerView.ViewHolder{
        TextView dogName, dogAge;

        public dogViewHolder(@NonNull View itemView) {
            super(itemView);

            dogName = (TextView)itemView.findViewById(R.id.TVname);
            dogAge = (TextView)itemView.findViewById(R.id.TVage);
        }
    }
}
