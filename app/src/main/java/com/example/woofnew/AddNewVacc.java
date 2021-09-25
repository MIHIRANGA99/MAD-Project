package com.example.woofnew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

public class AddNewVacc extends AppCompatActivity {

    EditText vaccnum,vaccname,vaccdate;
    ImageButton updatebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_vacc);

        //vaccnum = (EditText)findViewById(R.id.vaccinationnumber);

    }
}