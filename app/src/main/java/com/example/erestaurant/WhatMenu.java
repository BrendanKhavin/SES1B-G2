package com.example.erestaurant;




import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class WhatMenu extends AppCompatActivity {

    Button mLunchBtn, mDinnerBtn,mBackBtn;
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