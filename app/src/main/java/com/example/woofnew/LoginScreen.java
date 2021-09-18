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

public class LoginScreen extends AppCompatActivity {

    EditText emailInput, passwordInput;
    Button loginBttn;

    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        emailInput = findViewById(R.id.et_Email);
        passwordInput = findViewById(R.id.et_Password);
        loginBttn = findViewById(R.id.bttn_loginPge);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        progressDialog = new ProgressDialog(this);

        loginBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogin();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mUser != null){
            openProfiles();
        }
    }

    private void performLogin() {
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        if(!email.matches(emailPattern)){
            emailInput.setError("Email Is Not Valid");
        }
        else if(password.isEmpty()){
            passwordInput.setError("Enter a Password");
        }
        else {
            progressDialog.setMessage("LOGGING IN");
            progressDialog.setTitle("LOGIN");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        progressDialog.dismiss();
                        openProfiles();
                        Toast.makeText(LoginScreen.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(LoginScreen.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void openProfiles(){
        Intent intent = new Intent(LoginScreen.this, DogProfiles.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        this.finish();
    }

}