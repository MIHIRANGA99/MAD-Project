package com.example.woofnew;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;

public class UpdateDog extends AppCompatActivity {

    EditText name,age,gender,breed,weight;

    ShapeableImageView updateProPicSIV;

    Button updateDetails, uploadImageBttn;

    private static final int PICK_IMAGE_REQUEST = 1;

    private String dogId;

    ProgressDialog progressDialog;

    Uri mImageUri;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    DatabaseReference reference;
    StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_dog);

        Intent intent = getIntent();

        String dogName = intent.getStringExtra("dogName");
        String dogAge = intent.getStringExtra("dogAge");
        String dogGender = intent.getStringExtra("dogGender");
        String dogBreed = intent.getStringExtra("dogBreed");
        String dogWeight = intent.getStringExtra("dogWeight");
        dogId = intent.getStringExtra("dogId");
        String proPicURL = intent.getStringExtra("profilePicURL");

        name = findViewById(R.id.updateNameET);
        age = findViewById(R.id.updateAgeET);
        gender = findViewById(R.id.updateGenderET);
        breed = findViewById(R.id.updateBreedET);
        weight = findViewById(R.id.updateWeightET);

        updateProPicSIV = findViewById(R.id.updateProPicSIV);

        progressDialog = new ProgressDialog(this);

        name.setText(dogName.toString());
        age.setText(dogAge.toString());
        gender.setText(dogGender.toString());
        breed.setText(dogBreed.toString());
        weight.setText(dogWeight.toString());

        reference = FirebaseDatabase.getInstance().getReference("Users");

        storageRef = FirebaseStorage.getInstance().getReference("Profile Pictures");



        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        updateDetails = findViewById(R.id.updateDetailsBttn);

        uploadImageBttn = findViewById(R.id.uploadImageBttn);

        uploadImageBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();

            }
        });

        //-------------------------------------------------------------------------------------UPDATE DOG FUNCTION------------------------------------------------------------
        updateDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reference.child(mUser.getUid()).child("Dogs").child(dogId).child("dogName").setValue(name.getText().toString());
                reference.child(mUser.getUid()).child("Dogs").child(dogId).child("dogAge").setValue(age.getText().toString());
                reference.child(mUser.getUid()).child("Dogs").child(dogId).child("dogGender").setValue(gender.getText().toString());
                reference.child(mUser.getUid()).child("Dogs").child(dogId).child("dogBreed").setValue(breed.getText().toString());
                reference.child(mUser.getUid()).child("Dogs").child(dogId).child("dogWeight").setValue(weight.getText().toString());

                Toast.makeText(UpdateDog.this, "Details Updated", Toast.LENGTH_SHORT).show();
            }
        });

        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------
    }

    private void openGallery() {
        Intent intent1 = new Intent();
        intent1.setType("image/*");
        intent1.setAction(Intent.ACTION_GET_CONTENT);
        galleryActivityResultLauncher.launch(intent1);
    }



    private ActivityResultLauncher<Intent> galleryActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        Uri imageUri = data.getData();

                        updateProPicSIV.setImageURI(imageUri);

                        uploadFile(imageUri);
                    }else{
                        Toast.makeText(UpdateDog.this, "Cancelled...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    private void uploadFile(Uri uri) {

        if(uri != null){
            StorageReference fileRef = storageRef.child(System.currentTimeMillis() + "." + getFileExtension(uri));
            fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    progressDialog.dismiss();
//                    Toast.makeText(UpdateDog.this, "Upload Successful", Toast.LENGTH_SHORT).show();
//
//                    reference.child(mUser.getUid()).child("Dogs").child(dogId).child("imageURL").setValue(Objects.requireNonNull(Objects.requireNonNull(taskSnapshot.getMetadata()).getReference()).getDownloadUrl().toString());

                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            progressDialog.dismiss();
                            Toast.makeText(UpdateDog.this, "Upload Successful", Toast.LENGTH_SHORT).show();
                            reference.child(mUser.getUid()).child("Dogs").child(dogId).child("imageURL").setValue(uri.toString());

                        }
                    });
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                    progressDialog.setMessage("Upload in Progress: " + ((double)progress));
                    progressDialog.setTitle("Uploading...");
                    progressDialog.show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(UpdateDog.this, "Cant Upload the Image", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            Toast.makeText(UpdateDog.this, "Upload Failed", Toast.LENGTH_SHORT).show();
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
}