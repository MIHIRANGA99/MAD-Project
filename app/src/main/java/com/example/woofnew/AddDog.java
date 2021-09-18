package com.example.woofnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.UUID;

public class AddDog extends AppCompatActivity {

    EditText et_name, et_age, et_gender, et_breed, et_weight;
    Button addDog_bttn;

    ProgressDialog progressDialog;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    String dogID;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dog);

        et_name = findViewById(R.id.et_DOGNAME);
        et_age = findViewById(R.id.et_DOGAGE);
        et_gender = findViewById(R.id.et_DOGGENDER);
        et_breed = findViewById(R.id.et_DOGBREED);
        et_weight = findViewById(R.id.et_DOGWEIGHT);

        addDog_bttn = findViewById(R.id.bttn_addDog);

        progressDialog = new ProgressDialog(this);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Dogs");

        addDog_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.setMessage("Creating Dog Profile");
                progressDialog.setTitle("CREATING");
                progressDialog.show();

                String dogName = et_name.getText().toString();
                String dogAge = et_age.getText().toString();
                String dogGender = et_gender.getText().toString();
                String dogBreed = et_breed.getText().toString();
                String dogWeight = et_weight.getText().toString();
                dogID = UUID.randomUUID().toString();

                DogRVModel dogRVModel = new DogRVModel(dogName,dogAge,dogGender,dogBreed,dogWeight,dogID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        progressDialog.dismiss();

                        databaseReference.child(dogID).setValue(dogRVModel);
                        Toast.makeText(AddDog.this, "Your Dog Profile Created", Toast.LENGTH_SHORT).show();

                        intent = new Intent(AddDog.this, Navigation.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        progressDialog.dismiss();

                        Toast.makeText(AddDog.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}