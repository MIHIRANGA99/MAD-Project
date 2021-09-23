package com.example.woofnew;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    private ImageButton EditDetailsBttn;

    private String dogNAME;
    private String dogAGE;
    private String dogGENDER;
    private String dogBREED;
    private String dogWeight;
    private String dogID;
    private String profilePic;

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

        TVdogName = profile.findViewById(R.id.TV_dogName);
        ProfilePicSIV = profile.findViewById(R.id.profilePic_Profile);

        EditDetailsBttn = profile.findViewById(R.id.editDetailsBttn);

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
        Glide.with(requireContext()).load(profilePic).into(ProfilePicSIV);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        return profile;
    }

}