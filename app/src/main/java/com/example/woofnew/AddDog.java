package com.example.woofnew;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

public class AddDog extends AppCompatActivity {

    EditText et_name, et_age, et_gender, et_breed, et_weight;
    Button addDog_bttn;

    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    StorageReference reference = FirebaseStorage.getInstance().getReference();

    Uri imageUri;

    String dogID;
    String imageURL;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dog);

        et_name = findViewById(R.id.et_DOGNAME);
        et_age = findViewById(R.id.et_DOGAGE);
        et_gender = findViewById(R.id.et_DOGGENDER);
        et_breed = findViewById(R.id.et_DOGBREED);
        et_weight = findViewById(R.id.et_DOGWEIGHT);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        addDog_bttn = findViewById(R.id.bttn_addDog);

        imageURL = "https://firebasestorage.googleapis.com/v0/b/woof-14cb1.appspot.com/o/dog-3897530_1280.png?alt=media&token=17ee8def-1146-40a2-a698-d35ca6730f59";

        Intent i = getIntent();
        String userID =  i.getStringExtra("userID");

        progressDialog = new ProgressDialog(this);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");

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

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        progressDialog.dismiss();

                        databaseReference.child(mUser.getUid()).child("Dogs").child(dogID).setValue(dogRVModel);
                        Toast.makeText(AddDog.this, "Your Dog Profile Created", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(AddDog.this, Navigation.class);
                        intent.putExtra("dogNAME", dogName.toString());
                        intent.putExtra("dogAGE", dogAge.toString());
                        intent.putExtra("dogGENDER", dogGender.toString());
                        intent.putExtra("dogBREED", dogBreed.toString());
                        intent.putExtra("dogWEIGHT", dogWeight.toString());
                        intent.putExtra("dogID", dogID.toString());
                        intent.putExtra("proImgURL", imageURL.toString());
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        progressDialog.dismiss();

                        Toast.makeText(AddDog.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------
    }
}