package com.example.erestaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    //new
    //TextView mFullName, mAge, mFavFood, mEmail;
    //end
    Button mProfile, mOrder, mHistory;
    //DatabaseReference reff2;
    //FirebaseAuth fAuth;

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

        //fAuth = FirebaseAuth.getInstance();

        mOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),booking.class));
            }
        });

        mProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),profile.class));
            }
        });

    }
    public void logout(View view){ //log out my user and send to login page
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }


}