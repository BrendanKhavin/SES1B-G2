package com.example.eRestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class menuUpdateMain extends AppCompatActivity {

    Button mbackBtn, mlunchUpdateBtn, mdinnerupdatebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_update_main);

        mbackBtn = findViewById(R.id.backBtn);
        mlunchUpdateBtn = findViewById(R.id.lunchpgBtn);
        mdinnerupdatebtn = findViewById(R.id.dinnerpgBtn);

        //fAuth = FirebaseAuth.getInstance();

        mbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), com.example.eRestaurant.staffmain.class));
            }
        });
        mlunchUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LunchMenuUpdater.class));
            }
        });
        mdinnerupdatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DinnerMenuUpdater.class));
            }
        });

    }
}