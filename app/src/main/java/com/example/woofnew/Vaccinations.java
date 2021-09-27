package com.example.woofnew;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Vaccinations#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Vaccinations extends Fragment {

    String dogNAME,dogAGE,dogGENDER,dogBREED,dogWeight,dogID;

    RecyclerView vaccinationlist;
    VaccineAdapter vacAdapter;

    ImageButton btnaddnewvac;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Vaccinations() {
        // Required empty public constructor
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Vaccinations.
     */
    // TODO: Rename and change types and number of parameters
    public static Vaccinations newInstance(String param1, String param2) {
        Vaccinations fragment = new Vaccinations();
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

        View vaccines = inflater.inflate(R.layout.fragment_vaccinations, container, false);

        btnaddnewvac = (ImageButton) vaccines.findViewById(R.id.btnaddnewvac);
        btnaddnewvac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getContext(),AddNewVacc.class);
                intent2.putExtra("dogID",dogID);
                startActivity(intent2);
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

        }

        vaccinationlist = (RecyclerView) vaccines.findViewById(R.id.vaccinationlist_rv);
        vaccinationlist.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();

        assert mUser != null;
        FirebaseRecyclerOptions<VaccinationModel> options=
        new FirebaseRecyclerOptions.Builder<VaccinationModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference("Users").child(mUser.getUid()).child("Dogs").child(dogID).child("Vaccinations"),VaccinationModel.class)
                .build();

        vacAdapter = new VaccineAdapter(options);
        vaccinationlist.setAdapter(vacAdapter);


        // Inflate the layout for this fragment
        return vaccines;
    }

    @Override
    public void onStart() {
        super.onStart();
        vacAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        vacAdapter.stopListening();
    }
}