package com.example.woofnew;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DogBook#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DogBook extends Fragment {

    private String dogbookstr;

    private TextView dogsnamebf;
    private TextView dogAgebf;
    private TextView dogBreedbf;
    private TextView dogGenderbf;
    private TextView dogWeightbf;

    private ShapeableImageView profilepicbf;


    private String dogNAME;
    private String dogAGE;
    private String dogGENDER;
    private String dogBREED;
    private String dogWeight;
    private String dogID;
    private String profilePic;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DogBook() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DogBook newInstance(String param1, String param2) {
        DogBook fragment = new DogBook();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View book =  inflater.inflate(R.layout.fragment_dog_book, container, false);

        dogsnamebf = book.findViewById(R.id.TV_dogName);
        profilepicbf = book.findViewById(R.id.profilePic_Profile);


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

        dogsnamebf.setText(dogNAME);
        dogAgebf.setText(dogAGE);
        dogBreedbf.setText(dogBREED);
        dogGenderbf.setText(dogGENDER);
        dogWeightbf.setText(dogWeight);


        Glide.with(requireContext()).load(profilePic).into(profilepicbf);

        return book;
    }
}
