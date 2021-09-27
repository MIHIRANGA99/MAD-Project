package com.example.woofnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    Button rateDogs, vetList;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        rateDogs = findViewById(R.id.bttn_rateTheDogs);
        vetList = findViewById(R.id.bttn_lookingForVets);

        rateDogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this,DogsDetailsPageOne.class);
                startActivity(intent);
            }
        });

        vetList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this,VeterinarianList.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mUser != null){
            Intent intent = new Intent(MainActivity.this, DogProfiles.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();
        }
    }

    public void openLogin(View view){
        intent = new Intent(MainActivity.this, LoginScreen.class);
        startActivity(intent);
    }

    public void openReg(View view){
        intent = new Intent(MainActivity.this, RegisterScreen.class);
        startActivity(intent);
    }
}

