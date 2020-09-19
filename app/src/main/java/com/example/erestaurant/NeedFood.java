package com.example.erestaurant;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NeedFood extends AppCompatActivity {

    Button mYesBtn, mNoBtn,mBackBtn;
    RadioGroup radioGroup,radioGroup2, radioGroup3, radioGroup4;

    //database stuff
    BookingDetails booking;
    //FirebaseAuth fAuth;
    FirebaseAuth fAuth1;
    //collecting data
    DatabaseReference reff4, reff5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needfood);



        mYesBtn = findViewById(R.id.YesBtn);
        mNoBtn = findViewById(R.id.NoBtn);



        mYesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LunchMenu.class));
               // reff4 = FirebaseDatabase.getInstance().getReference().child("BookingDetails").child();
            }
        });


        mNoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),DinnerMenu.class));
            }
        });

    }
}