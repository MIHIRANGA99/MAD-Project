package com.example.woofnew;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class DogProfiles extends AppCompatActivity{

    private Button logout_bttn;
    private Button addNewDog_bttn;

    private Intent intent;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    //Firebase
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    //TextView
    private TextView ownerTV;

    //Recycler View
    private RecyclerView dogRV;

    //ArrayList
    private ArrayList<DogRVModel> dogRVModelArrayList;

    //Relative Layout
    private RelativeLayout dogProfilesRL;

    //Progress Dialog
    private ProgressDialog progressDialog;

    //RV Adapter
    private DogRVAdapter dogRVAdapter;

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

        //----------------------------------------------------------------------------------DRAWER------------------------------------------------------------------------------
        MaterialToolbar toolbar = findViewById(R.id.topAcBar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.drawer_view);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawerLayout.openDrawer(GravityCompat.START);

            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id)
                {
                    case R.id.nav_profiles:
                        Intent myDogs = new Intent(DogProfiles.this, DogProfiles.class);
                        startActivity(myDogs);
                        break;
                    case R.id.nav_woopedia:
                        Toast.makeText(DogProfiles.this, "Woopedia", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_vet:
                        Intent vet = new Intent(DogProfiles.this, VeterinarianList.class);
                        startActivity(vet);
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });

        //Initialize
        logout_bttn = findViewById(R.id.bttn_logout);
        addNewDog_bttn = findViewById(R.id.bttn_ADDNEW);

        ownerTV = findViewById(R.id.ownerTV);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        //---------------------------------------------------------------------USE THIS--------------------------------------------------------------------------------------------------
//        Intent i  = getIntent();
//        String ownerName = i.getStringExtra("ownerName");

//        Toast.makeText(DogProfiles.this, ownerName, Toast.LENGTH_SHORT).show();
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        firebaseDatabase = FirebaseDatabase.getInstance();

        dogProfilesRL = findViewById(R.id.RLDogProfiles);

        dogRV = (RecyclerView)findViewById(R.id.idRVDogs);
        dogRV.setLayoutManager(new LinearLayoutManager(this));


        //-----------------------------------------------------------------------------RETRIEVE FROM DATABASE-----------------------------------------------------------------
        FirebaseRecyclerOptions<DogRVModel> options =
                new FirebaseRecyclerOptions.Builder<DogRVModel>()
                .setQuery(firebaseDatabase.getReference().child("Users").child(mUser.getUid()).child("Dogs"), DogRVModel.class)
                .build();


        dogRVAdapter = new DogRVAdapter(options);
        dogRV.setAdapter(dogRVAdapter);

        firebaseDatabase.getReference().child("Users").child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ownerTV.setText(Objects.requireNonNull(snapshot.child("userName").getValue()).toString() + "'s Dogs");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DogProfiles.this, "Cannot Retrieve data", Toast.LENGTH_SHORT).show();
            }
        });
        //---------------------------------------------------------------------------------------------------------------------------------------------------------------------


        //------------------------------------------------------------------------------------LOGOUT---------------------------------------------------------------------------
        logout_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                
                intent = new Intent(DogProfiles.this, MainActivity.class);
                startActivity(intent);

                Toast.makeText(DogProfiles.this, "Logged out successful", Toast.LENGTH_SHORT).show();
            }
        });

        //----------------------------------------------------------------------------------ADD DOG INTENT---------------------------------------------------------------------------
        addNewDog_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(DogProfiles.this, AddDog.class);
                intent1.putExtra("userID", mUser.getUid().toString());
                startActivity(intent1);
            }
        });


    }
}