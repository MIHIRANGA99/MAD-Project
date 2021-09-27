package com.example.woofnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class RegisterScreen extends AppCompatActivity {

    EditText inputEmail, inputOwner, inputPassword, inputConfirmPassword;
    Button bttnRegister;
    ProgressDialog progressDialog;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        inputEmail = findViewById(R.id.et_EmailReg);
        inputOwner = findViewById(R.id.et_Owner);
        inputPassword = findViewById(R.id.et_Password);
        inputConfirmPassword = findViewById(R.id.et_PasswordRE);
        bttnRegister = findViewById(R.id.bttn_RegisterPge);

        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        bttnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerformAuth();
            }
        });
    }

    private void PerformAuth() {
        String email=inputEmail.getText().toString();
        String Owner = inputOwner.getText().toString();
        String password = inputPassword.getText().toString();
        String confirmPassword = inputConfirmPassword.getText().toString();

        if(!email.matches(emailPattern)){
            inputEmail.setError("Please enter a valid email");
        }
        else if(password.isEmpty() || password.length() < 6){
            inputPassword.setError("Please enter a password");
        }
        else if(Owner.isEmpty()){
            inputOwner.setError("Please enter your name");
        }
        else if(!password.equals(confirmPassword)){
            inputConfirmPassword.setError("Passwords are not matching");
        }
        else {
            progressDialog.setMessage("Registration in progress");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();

                        //CREATE USER IN DATABASE
                        HashMap<String, String> user = new HashMap<>();
                        user.put("userName", Owner);

                        FirebaseDatabase.getInstance().getReference().child("Users").child(Objects.requireNonNull(mAuth.getCurrentUser()).getUid()).setValue(user);

                        OpenProfiles(Owner);
                        Toast.makeText(RegisterScreen.this, "Registration Success", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(RegisterScreen.this, "Registration unsuccessful"+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void OpenProfiles(String Owner) {

        Intent intent= new Intent(RegisterScreen.this, DogProfiles.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("ownerName", Owner);
        startActivity(intent);
    }
}