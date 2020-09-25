package com.example.erestaurant;




import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class WhatMenu extends AppCompatActivity {

    Button mLunchBtn, mDinnerBtn, mBackBtn;
    String currentUserId;
    //FirebaseAuth fAuth;
    FirebaseAuth fAuth1;
    //collecting data
    DatabaseReference reff4, reff5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatmenu);




        mLunchBtn = findViewById(R.id.LunchBtn);
        mDinnerBtn = findViewById(R.id.DinnerBtn);





        mLunchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LunchMenuMAIN.class));
            }
        });

        mDinnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),DinnerMenuMAIN.class));
            }
        });

    }
}