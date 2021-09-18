package com.example.woofnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

