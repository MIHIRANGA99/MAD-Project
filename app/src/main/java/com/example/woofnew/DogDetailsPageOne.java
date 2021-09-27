package com.example.woofnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DogDetailsPageOne extends AppCompatActivity {

    RecyclerView recyclergroups;
    DatabaseReference refGroups = FirebaseDatabase.getInstance().getReference("Groups");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_details_page_one);
        recyclergroups = findViewById(R.id.recycler_groups);
        setGroups();
    }

    private  void setGroups() {
        ArrayList<GroupsModel> dogGroups = new ArrayList<>();
        GroupAdapter groupAdapter = new GroupAdapter(this, dogGroups);
        recyclergroups.setHasFixedSize(true);
        recyclergroups.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclergroups.setAdapter(groupAdapter);

        refGroups.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    GroupsModel groupsModel = dataSnapshot.getValue(GroupsModel.class);
                    dogGroups.add(groupsModel);
                }
                groupAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}