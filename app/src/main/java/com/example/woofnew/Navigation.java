package com.example.woofnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Navigation extends AppCompatActivity {

    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        navigationView = findViewById(R.id.NavBar);
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new DogProfile()).commit();
        navigationView.setSelectedItemId(R.id.nav_home);
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new DogProfile()).commit();

        Intent i = getIntent();
        String dog_name = i.getStringExtra("dogNAME");
        String dog_age = i.getStringExtra("dogAGE");
        String dog_gender = i.getStringExtra("dogGENDER");
        String dog_breed = i.getStringExtra("dogBREED");
        String dog_weight = i.getStringExtra("dogWEIGHT");
        String dog_id = i.getStringExtra("dogID");
        String profilePic_URL = i.getStringExtra("proImgURL");

        Bundle details = new Bundle();
        details.putString("dogName", dog_name);
        details.putString("dogAge", dog_age);
        details.putString("dogGender", dog_gender);
        details.putString("dogBreed", dog_breed);
        details.putString("dogWeight", dog_weight);
        details.putString("dogId", dog_id);
        details.putString("profilePicURL",profilePic_URL);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.nav_home:
                        fragment = new DogProfile();
                        fragment.setArguments(details);
                        break;

                    case R.id.nav_book:
                        fragment = new DogBook();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment).commit();


                return true;
            }
        });
    }
}