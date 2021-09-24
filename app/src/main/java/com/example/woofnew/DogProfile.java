package com.example.woofnew;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DogProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DogProfile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView TVdogName;

    private ShapeableImageView ProfilePicSIV;

    private ImageButton EditDetailsBttn, uploadImagesBttn;

    private String dogNAME;
    private String dogAGE;
    private String dogGENDER;
    private String dogBREED;
    private String dogWeight;
    private String dogID;
    private String profilePic;

    RecyclerView mRecyclerView;
    ImageAdapter mAdapter;

    private DatabaseReference mDatabaseRef;
//    private List<Uploadpics> mUploads;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    public DogProfile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DogProfile.
     */
    // TODO: Rename and change types and number of parameters
    public static DogProfile newInstance(String param1, String param2) {
        DogProfile fragment = new DogProfile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View profile =  inflater.inflate(R.layout.fragment_dog_profile, container, false);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        mRecyclerView = profile.findViewById(R.id.db_images_RV);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);






        TVdogName = profile.findViewById(R.id.TV_dogName);
        ProfilePicSIV = profile.findViewById(R.id.profilePic_Profile);

        EditDetailsBttn = profile.findViewById(R.id.editDetailsBttn);
        uploadImagesBttn = profile.findViewById(R.id.upload_images_bttn);





        //------------------------------------------------------------------------------EDIT DOG DETAILS INTENT---------------------------------------------------------------
        EditDetailsBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),UpdateDog.class);
                intent.putExtra("dogName",dogNAME);
                intent.putExtra("dogAge",dogAGE);
                intent.putExtra("dogGender",dogGENDER);
                intent.putExtra("dogBreed",dogBREED);
                intent.putExtra("dogWeight",dogWeight);
                intent.putExtra("dogId", dogID);
                intent.putExtra("profilePicURL", profilePic);
                startActivity(intent);
            }
        });
        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------

        Bundle details = getArguments();

        if(details != null){
            dogNAME = details.getString("dogName");
            dogAGE = details.getString("dogAge");
            dogGENDER = details.getString("dogGender");
            dogBREED = details.getString("dogBreed");
            dogWeight = details.getString("dogWeight");
            dogID = details.getString("dogId");
            profilePic = details.getString("profilePicURL");
        }

        TVdogName.setText(dogNAME);
        Picasso.get().load(profilePic).resize(300,300).centerCrop().into(ProfilePicSIV);




        //----------------------------------------------------------------------UPLOAD IMAGES INTENT------------------------------------------------------------------------
        uploadImagesBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),UploadImages.class);
                intent.putExtra("dogId", dogID);
                startActivity(intent);
            }
        });
        //------------------------------------------------------------------------------------------------------------------------------------------------------------------



        //----------------------------------------------------------------------RETRIEVE IMAGES IN DOG DASHBOARD------------------------------------------------------------
        FirebaseRecyclerOptions<Uploadpics> options =
                new FirebaseRecyclerOptions.Builder<Uploadpics>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Users").child(mUser.getUid()).child("Dogs").child(dogID).child("images"), Uploadpics.class)
                        .build();

        mAdapter = new ImageAdapter(options);
        mRecyclerView.setAdapter(mAdapter);
        //------------------------------------------------------------------------------------------------------------------------------------------------------------------



        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        return profile;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }
}