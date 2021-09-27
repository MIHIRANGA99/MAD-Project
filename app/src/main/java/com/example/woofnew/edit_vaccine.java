package com.example.woofnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class edit_vaccine extends AppCompatActivity {

    EditText vacName,vacNum,vacDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vaccine);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();

        Intent i = getIntent();
        String DogID = i.getStringExtra("DogID");
        String vaccNum = i.getStringExtra("vacc_num");
        String vaccName = i.getStringExtra("vacc_name");
        String vaccDate = i.getStringExtra("vacc_due");



        vacName = findViewById(R.id.editvaccinationnumber);
        vacNum = findViewById(R.id.editvaccinationname);
        vacDate = findViewById(R.id.editvaccinationdate);

        FirebaseDatabase.getInstance().getReference("Users").child(mUser.getUid()).child("Dogs").child(DogID).child("Vaccinations")
                .child("vacc_name").setValue(vacName.getText().toString());

        FirebaseDatabase.getInstance().getReference("Users").child(mUser.getUid()).child("Dogs").child(DogID).child("Vaccinations")
                .child("vacc_num").setValue(vacNum.getText().toString());

        FirebaseDatabase.getInstance().getReference("Users").child(mUser.getUid()).child("Dogs").child(DogID).child("Vaccinations")
                .child("vacc_date").setValue(vacDate.getText().toString());
    }
}