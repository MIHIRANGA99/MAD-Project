package com.example.woofnew;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

public class AddDog extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText et_name, et_gender, et_breed, et_weight;
    private TextView et_age;
    private Button addDog_bttn;

    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    private FirebaseDatabase firebaseDatabase;
//    DatabaseReference databaseReference;

    private StorageReference reference = FirebaseStorage.getInstance().getReference();

    private Uri imageUri;

    private String dogID;
    private String imageURL;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dog);

        et_name = findViewById(R.id.et_DOGNAME);
        et_age = findViewById(R.id.et_DOGAGE);
        et_gender = findViewById(R.id.et_DOGGENDER);
        et_breed = findViewById(R.id.et_DOGBREED);
        et_weight = findViewById(R.id.et_DOGWEIGHT);

        et_age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new datePickerFragment();
                datePicker.show(getSupportFragmentManager(), "Date Picker");
            }
        });

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        addDog_bttn = findViewById(R.id.bttn_addDog);

        imageURL = "https://firebasestorage.googleapis.com/v0/b/woof-14cb1.appspot.com/o/dog-3897530_1280.png?alt=media&token=17ee8def-1146-40a2-a698-d35ca6730f59";

        Intent i = getIntent();
        String userID =  i.getStringExtra("userID");

        progressDialog = new ProgressDialog(this);

        firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference("Users");

        //--------------------------------------------------------------------------------ADD DOG FUNCTION--------------------------------------------------------------------
        addDog_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.setMessage("Creating Dog Profile");
                progressDialog.setTitle("CREATING");
                progressDialog.show();

                String dogName = et_name.getText().toString();
                String dogAge = et_age.getText().toString();
                String dogGender = et_gender.getText().toString();
                String dogBreed = et_breed.getText().toString();
                String dogWeight = et_weight.getText().toString();
                dogID = UUID.randomUUID().toString();



                DogRVModel dogRVModel = new DogRVModel(dogName,dogAge,dogGender,dogBreed,dogWeight,imageURL,dogID);
                databaseReference.child(mUser.getUid()).child("Dogs").child(dogID).setValue(dogRVModel);
                Toast.makeText(AddDog.this, dogName +"'s Profile Created", Toast.LENGTH_SHORT).show();

                goDogProfile();

                progressDialog.dismiss();

//                databaseReference.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                        progressDialog.dismiss();
//
//                        databaseReference.child(mUser.getUid()).child("Dogs").child(dogID).setValue(dogRVModel);
//                        Toast.makeText(AddDog.this, dogName +"'s Profile Created", Toast.LENGTH_SHORT).show();
//
//                        goDogProfile();
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                        progressDialog.dismiss();
//
//                        Toast.makeText(AddDog.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });
        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------
    }

    public void goDogProfile(){
        Intent intent5 = new Intent(AddDog.this, DogProfiles.class);
//                        intent5.putExtra("dogNAME", dogName.toString());
//                        intent5.putExtra("dogAGE", dogAge.toString());
//                        intent5.putExtra("dogGENDER", dogGender.toString());
//                        intent5.putExtra("dogBREED", dogBreed.toString());
//                        intent5.putExtra("dogWEIGHT", dogWeight.toString());
//                        intent5.putExtra("dogID", dogID.toString());
//                        intent5.putExtra("proImgURL", imageURL.toString());
        startActivity(intent5);
        finish();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        et_age.setText(DateFormat.getDateInstance(DateFormat.FULL).format(cal.getTime()));
    }
}