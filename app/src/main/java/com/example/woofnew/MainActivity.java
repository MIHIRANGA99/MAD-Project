package com.example.woofnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    Button rateDogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rateDogs = findViewById(R.id.bttn_rateTheDogs);
        rateDogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this,DogsDetailsPageOne.class);
                startActivity(intent);
            }
        });

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

