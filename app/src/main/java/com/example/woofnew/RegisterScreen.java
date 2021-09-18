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

public class RegisterScreen extends AppCompatActivity {

    EditText inputEmail, inputOwnerName, inputPassword, inputConfirmPassword;
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
        inputOwnerName = findViewById(R.id.et_ownerName);
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
        String password = inputPassword.getText().toString();
        String confirmPassword = inputConfirmPassword.getText().toString();
        String ownerName = inputOwnerName.getText().toString();

        if(!email.matches(emailPattern)){
            inputEmail.setError("Please enter a valid email");
        }
        else if(ownerName.isEmpty()){
            inputOwnerName.setError("Please input owner name");
        }
        else if(password.isEmpty() || password.length() < 6){
            inputPassword.setError("Please enter a password");
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
                        OpenProfiles();
                        Toast.makeText(RegisterScreen.this, "Registrarion Success", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(RegisterScreen.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void OpenProfiles() {
        Intent intent= new Intent(RegisterScreen.this, DogProfiles.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}