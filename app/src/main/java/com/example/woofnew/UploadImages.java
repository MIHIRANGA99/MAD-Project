package com.example.woofnew;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class UploadImages extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    ShapeableImageView imageView;
    Button chooseImageBttn;
    Button uploadImageBttn;
    EditText imageNameET;
    ProgressBar progressBar;
    Uri imageUri;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    StorageReference mStorageRef;
    DatabaseReference mDatabaseRef;
    
    private StorageTask mUploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_images);

        Intent intent = getIntent();
        String dogId = intent.getStringExtra("dogId");

        chooseImageBttn = findViewById(R.id.bttn_chooseFile);
        uploadImageBttn = findViewById(R.id.bttn_uploadFile);
        imageNameET = findViewById(R.id.imageTitle_ET);
        imageView = findViewById(R.id.db_image_SIV);
        progressBar = findViewById(R.id.progress_bar);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        mStorageRef = FirebaseStorage.getInstance().getReference("dogImages");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Users").child(mUser.getUid()).child("Dogs").child(dogId).child("images");


        //-------------------------------------------------------------------CHOOSE IMAGE INTENT------------------------------------------------------------------------------
        chooseImageBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });
        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------


        //------------------------------------------------------------------UPLOAD IMAGES INTENT AND UPLOAD-------------------------------------------------------------------
        uploadImageBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUploadTask != null && mUploadTask.isInProgress()){

                    Toast.makeText(UploadImages.this, "Upload in Progress", Toast.LENGTH_SHORT).show();
                    
                }else {
                    
                    uploadFile();
                    
                }
            }
        });
    }

    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        if(imageUri != null){
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));

            mUploadTask = fileReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            progressBar.setProgress(0);
                                        }
                                    }, 500);
                                    Toast.makeText(UploadImages.this, "Upload Successful", Toast.LENGTH_SHORT).show();

                                    Uploadpics uploadpic = new Uploadpics(imageNameET.getText().toString().trim(), uri.toString());
                                    String uploadId = mDatabaseRef.push().getKey();
                                    mDatabaseRef.child(uploadId).setValue(uploadpic);
                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(UploadImages.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                    progressBar.setProgress((int)progress);
                }
            });
        }
        else{
            Toast.makeText(UploadImages.this, "No Files Selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            imageUri = data.getData();

            Glide.with(this).load(imageUri).into(imageView);
        }
    }
}