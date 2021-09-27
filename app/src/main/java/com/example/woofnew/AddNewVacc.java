package com.example.woofnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddNewVacc extends AppCompatActivity {

    EditText vaccnumvf,vaccnamevf,vaccdatevf;
    ImageButton addvaccbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_vacc);

        vaccnumvf = (EditText) findViewById((R.id.vaccnum));
        vaccnamevf = (EditText) findViewById(R.id.vaccname);
        vaccdatevf = (EditText) findViewById(R.id.vaccdate);

        addvaccbutton = (ImageButton) findViewById(R.id.updatebutton);

        addvaccbutton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertintovacc();
                clearvacdet();
            }
        }));




        //vaccnum = (EditText)findViewById(R.id.vaccinationnumber);

    }

    private void insertintovacc(){


        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();

        Intent i = getIntent();
        String dogID = i.getStringExtra("dogID");
        Map<String,Object>map= new HashMap<>();
        map.put("vacc_num",vaccnumvf.getText().toString());
        map.put("vacc_name",vaccnamevf.getText().toString());
        map.put("vacc_date",vaccdatevf.getText().toString());
        map.put("DogID", dogID);

        FirebaseDatabase.getInstance().getReference("Users").child(mUser.getUid()).child("Dogs").child(dogID).child("Vaccinations").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddNewVacc.this, "Vaccination Entered successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddNewVacc.this, "Vaccination entering failed", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void clearvacdet(){
        vaccnamevf.setText(" ");
        vaccnumvf.setText(" ");
        vaccdatevf.setText(" ");
    }
}