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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class VaccineAdapter extends FirebaseRecyclerAdapter<VaccinationModel,VaccineAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    Context context;
    public VaccineAdapter(@NonNull FirebaseRecyclerOptions<VaccinationModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull VaccinationModel model) {
        holder.vaccnum.setText(model.getVacc_num());
        holder.vaccname.setText(model.getVacc_name());
        holder.vaccdate.setText(model.getVacc_date());




        holder.vaccupdatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.vaccnum.getContext())
                        .setContentHolder(new ViewHolder(R.layout.vaccupdate_popup))
                        .setExpanded(true,800)
                        .create();

                View v = dialogPlus.getHolderView();

                EditText vnum = v.findViewById(R.id.vaccupdatenum);
                EditText vname = v.findViewById(R.id.updatevacname);
                EditText vdate = v.findViewById(R.id.updatevacdate);

                Button confbtn = v.findViewById(R.id.updateconbutton);

                vnum.setText(model.getVacc_num());
                vname.setText(model.getVacc_name());
                vdate.setText(model.getVacc_date());

                String dogID = model.getDogID();

                dialogPlus.show();



                confbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        FirebaseAuth mAuth = FirebaseAuth.getInstance();
                        FirebaseUser mUser = mAuth.getCurrentUser();


                        Map<String,Object>map=new HashMap<>();
                        map.put("vacc_num",vnum.getText().toString());
                        map.put("vacc_name",vname.getText().toString());
                        map.put("vacc_date",vdate.getText().toString());

                        FirebaseDatabase.getInstance().getReference("Users").child(mUser.getUid().toString()).child("Dogs").child(dogID).child("Vaccinations")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.vaccnum.getContext() ,"Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.vaccnum.getContext() ,"Cannot Update", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });

                    }
                });
            }
        });


        holder.vaccdeletebtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                FirebaseUser mUser = mAuth.getCurrentUser();
                String dogID = model.getDogID();

                AlertDialog.Builder builder = new AlertDialog.Builder(holder.vaccnum.getContext());

                builder.setTitle("Vaccination will be deleted!");
                builder.setMessage("You can not undo this deletion");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference("Users").child(mUser.getUid().toString()).child("Dogs").child(dogID).child("Vaccinations")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.vaccnum.getContext(), "Cancelled.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }





    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vaccination_holder,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView vaccnum,vaccname,vaccdate;

        Button vaccupdatebtn,vaccdeletebtn,confbtn;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            vaccnum = (TextView)itemView.findViewById(R.id.vaccinationnumber);
            vaccname = (TextView)itemView.findViewById(R.id.vaccinationname);
            vaccdate = (TextView)itemView.findViewById(R.id.vaccinationdate);





            vaccupdatebtn = (Button)itemView.findViewById(R.id.vaccupdatebtn) ;
            vaccdeletebtn = (Button) itemView.findViewById(R.id.vaccdeletebtn);

        }
    }



}
