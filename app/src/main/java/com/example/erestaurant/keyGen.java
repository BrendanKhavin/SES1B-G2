package com.example.erestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class keyGen extends AppCompatActivity {

    Button mbackButton, mUploadBtn;
    EditText mStaffKey;
    FirebaseAuth fAuth;
    //collecting data
    DatabaseReference reff;
    StaffKeys staffKeys;
  //  String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_gen);

        fAuth = FirebaseAuth.getInstance();

        mbackButton = findViewById(R.id.backBtn);
        mUploadBtn = findViewById(R.id.uploadBtn);
        mStaffKey = findViewById(R.id.staffkeyHolder);
        staffKeys = new StaffKeys();

        reff = FirebaseDatabase.getInstance().getReference().child("StaffKeys");


        if(fAuth.getCurrentUser() == null) {
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        }

        mUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = mStaffKey.getText().toString().trim();
                if(key.length() != 9) {
                    mStaffKey.setError("Key must be 9 characters long");
                    return;
                }else{
                    staffKeys.setStaffKey(key);
                    reff.setValue(staffKeys);
                    Toast.makeText(keyGen.this, "Key Created", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext(), staffmain.class));
                }
            }
        });


        mbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), staffmain.class));
            }
        });

    }
}