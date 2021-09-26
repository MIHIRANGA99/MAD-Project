package com.example.woofnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

public class VetProfile extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet_profile);

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        if (getIntent().hasExtra("vet_name") && getIntent().hasExtra("vet_contact") && getIntent().hasExtra("vet_address") && getIntent().hasExtra("vet_email")){
            String vetName = getIntent().getStringExtra("vet_name");
            String vetContact = getIntent().getStringExtra("vet_contact");
            String vetEmail = getIntent().getStringExtra("vet_email");
            String vetAddress = getIntent().getStringExtra("vet_address");
            setData(vetName, vetContact, vetEmail, vetAddress);
        }
    }
    private void setData(String vetName, String vetContact, String vetEmail, String vetAddress){
        TextView vetriName = findViewById(R.id.doc_name);
        vetriName.setText("Dr."+vetName);
        TextView vetriContact = findViewById(R.id.phone_doc);
        vetriContact.setText(vetContact);
        TextView vetriEmail = findViewById(R.id.mail_doc);
        vetriEmail.setText(vetEmail);
        TextView vetriAddress = findViewById(R.id.address_doc);
        vetriAddress.setText(vetAddress);

    }
}