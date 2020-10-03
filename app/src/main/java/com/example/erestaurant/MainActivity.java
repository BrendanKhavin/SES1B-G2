package com.example.erestaurant;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    //new
    //TextView mFullName, mAge, mFavFood, mEmail;
    //end
    private DatabaseReference reff;
    private FirebaseAuth bAuth;
    String currentUserID;
    Button mProfile, mOrder, mHistory, mMenu;
    //DatabaseReference reff2;
    //FirebaseAuth fAuth;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //new
        //mFullName = findViewById(R.id.zfullname);
        //mAge = findViewById(R.id.zAge);
        //mEmail = findViewById(R.id.zEmail);
        //mFavFood = findViewById(R.id.zFaveFood);
        //end

        mProfile = findViewById(R.id.profileBtn);
        mOrder = findViewById(R.id.orderBtn);
        mHistory = findViewById(R.id.historyBtn);
        mMenu = findViewById(R.id.viewMenuBtn);

        bAuth = FirebaseAuth.getInstance();

        if (bAuth.getCurrentUser() == null) {
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        }

        currentUserID = bAuth.getCurrentUser().getUid();
        reff = FirebaseDatabase.getInstance().getReference().child("BookingDetails").child(currentUserID);

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    mHistory.setEnabled(false);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });




        mOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    mOrder.setEnabled(false);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }});


            mOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), booking.class));
            }
        });

        //ViewMenu

        mMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), WhatMenu.class));
            }
        });


        mProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), profile.class));
            }
        });

        mHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), History.class));
            }
        });

    }
        public void logout (View view){ //log out my user and send to login page
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        }
}