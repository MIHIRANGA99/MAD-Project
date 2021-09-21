package com.example.woofnew;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class DogProfiles extends AppCompatActivity{

    Button logout_bttn;
    Button addNewDog_bttn;
    
    Intent intent;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    //Firebase
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    //Recycler View
    RecyclerView dogRV;

    //ArrayList
    ArrayList<DogRVModel> dogRVModelArrayList;

    //Relative Layout
    RelativeLayout dogProfilesRL;

    //Progress Dialog
    ProgressDialog progressDialog;

    //RV Adapter
    DogRVAdapter dogRVAdapter;

    @Override
    protected void onStart() {
        super.onStart();
        dogRVAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dogRVAdapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_profiles);

        //Initialize
        logout_bttn = findViewById(R.id.bttn_logout);
        addNewDog_bttn = findViewById(R.id.bttn_ADDNEW);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        //---------------------------------------------------------------------USE THIS--------------------------------------------------------------------------------------------------
        Intent i  = getIntent();
        String ownerName = i.getStringExtra("ownerName");
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        firebaseDatabase = FirebaseDatabase.getInstance();

        dogProfilesRL = findViewById(R.id.RLDogProfiles);

        dogRV = (RecyclerView)findViewById(R.id.idRVDogs);
        dogRV.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<DogRVModel> options =
                new FirebaseRecyclerOptions.Builder<DogRVModel>()
                .setQuery(firebaseDatabase.getReference().child("Users").child(mUser.getUid()).child("Dogs"), DogRVModel.class)
                .build();


        dogRVAdapter = new DogRVAdapter(options);
        dogRV.setAdapter(dogRVAdapter);


        //LOGOUT
        logout_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                
                intent = new Intent(DogProfiles.this, MainActivity.class);
                startActivity(intent);

                Toast.makeText(DogProfiles.this, "Logged out successful", Toast.LENGTH_SHORT).show();
            }
        });

        //ADD DOG
        addNewDog_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(DogProfiles.this, AddDog.class);
                intent.putExtra("userID", mUser.getUid().toString());
                startActivity(intent);
            }
        });


    }
}