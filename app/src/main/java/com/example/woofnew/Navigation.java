package com.example.woofnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

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
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new DogBook()).commit();

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.nav_home:
                        fragment = new DogProfile();
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