package com.example.woofnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class DogDetailsPageOne extends AppCompatActivity {

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_details_page_one);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_groups);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}