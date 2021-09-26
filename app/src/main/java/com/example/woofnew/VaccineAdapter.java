package com.example.woofnew;
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

public class VaccineAdapter extends FirebaseRecyclerAdapter<VaccinationModel,VaccineAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public VaccineAdapter(@NonNull FirebaseRecyclerOptions<VaccinationModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull VaccinationModel model) {
        holder.vaccnum.setText(model.getVacc_num());
        holder.vaccname.setText(model.getVacc_name());
        holder.vaccdate.setText(model.getVacc_date());

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vaccination_holder,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

            TextView vaccnum,vaccname,vaccdate;

            public myViewHolder(@NonNull View itemView) {
                super(itemView);

                vaccnum = (TextView)itemView.findViewById(R.id.vaccinationnumber);
                vaccname = (TextView)itemView.findViewById(R.id.vaccinationname);
                vaccdate = (TextView)itemView.findViewById(R.id.vaccinationdate);

            }
        }

}
