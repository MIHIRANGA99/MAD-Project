package com.example.woofnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateDog extends AppCompatActivity {

    EditText name,age,gender,breed,weight;

    Button updateDetails;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_dog);

        Intent intent = getIntent();

        String dogName = intent.getStringExtra("dogName");
        String dogAge = intent.getStringExtra("dogAge");
        String dogGender = intent.getStringExtra("dogGender");
        String dogBreed = intent.getStringExtra("dogBreed");
        String dogWeight = intent.getStringExtra("dogWeight");
        String dogId = intent.getStringExtra("dogId");
        String proPicURL = intent.getStringExtra("profilePicURL");

        name = findViewById(R.id.updateNameET);
        age = findViewById(R.id.updateAgeET);
        gender = findViewById(R.id.updateGenderET);
        breed = findViewById(R.id.updateBreedET);
        weight = findViewById(R.id.updateWeightET);

        name.setText(dogName.toString());
        age.setText(dogAge.toString());
        gender.setText(dogGender.toString());
        breed.setText(dogBreed.toString());
        weight.setText(dogWeight.toString());

        reference = FirebaseDatabase.getInstance().getReference("Users");

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        updateDetails = findViewById(R.id.updateDetailsBttn);

        updateDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reference.child(mUser.getUid()).child("Dogs").child(dogId).child("dogName").setValue(name.getText().toString());
                reference.child(mUser.getUid()).child("Dogs").child(dogId).child("dogAge").setValue(age.getText().toString());
                reference.child(mUser.getUid()).child("Dogs").child(dogId).child("dogGender").setValue(gender.getText().toString());
                reference.child(mUser.getUid()).child("Dogs").child(dogId).child("dogBreed").setValue(breed.getText().toString());
                reference.child(mUser.getUid()).child("Dogs").child(dogId).child("dogWeight").setValue(weight.getText().toString());

            }
        });
    }
}