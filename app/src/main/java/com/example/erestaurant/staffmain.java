package com.example.erestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class staffmain extends AppCompatActivity {

    Button mUpdateMenus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staffmain);

        mUpdateMenus = findViewById(R.id.menuupdateBtn);


        //fAuth = FirebaseAuth.getInstance();

        mUpdateMenus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),menuUpdateMain.class));
            }
        });

    }

    public void logout(View view){ //log out my user and send to login page
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
}