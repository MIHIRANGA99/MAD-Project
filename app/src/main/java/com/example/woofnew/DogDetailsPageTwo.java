package com.example.woofnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DogDetailsPageTwo extends AppCompatActivity {

    private RecyclerView recyclerBreeds;

    String groupName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_details_page_two);
        recyclerBreeds = findViewById(R.id.recycler_breeds);
        getIncomingIntent();
        setBreeds();
    }

    private void getIncomingIntent(){
        if (getIntent().hasExtra("group_name") && getIntent().hasExtra("group_details")){
            groupName = getIntent().getStringExtra("group_name");
            String groupDetails = getIntent().getStringExtra("group_details");
            setData(groupName, groupDetails);
        }
    }

    private void setData(String groupName, String groupDetails){
        TextView grpName = findViewById(R.id.title_breeds);
        grpName.setText(groupName);
        TextView grpDetails = findViewById(R.id.grp_details);
        grpDetails.setText(groupDetails);
    }

    private  void setBreeds() {

        ArrayList<BreedsModel> dogBreeds = new ArrayList<>();
        BreedAdapter breedAdapter = new BreedAdapter(this,dogBreeds);
        recyclerBreeds.setHasFixedSize(true);
        recyclerBreeds.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerBreeds.setAdapter(breedAdapter);

        FirebaseDatabase.getInstance().getReference("Groups").child(groupName).child("Breeds").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    BreedsModel breedsModel = dataSnapshot.getValue(BreedsModel.class);
                    dogBreeds.add(breedsModel);
                }
                breedAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}