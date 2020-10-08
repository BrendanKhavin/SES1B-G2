package com.example.erestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class tableLimit extends AppCompatActivity {


    //database stuff
    bookingLimitGlobals globalLimit;
    //FirebaseAuth fAuth;
    FirebaseAuth fAuth1;
    //collecting data
    DatabaseReference reff;
    //sub branches
    DatabaseReference reff1, reff2, reff3, reff4, reff5, reff6, reff7, reff8;
    EditText mSesh1, mSesh2, mSesh3, mSesh4, mSesh5, mSesh6, mSesh7, mSesh8;
    Button mbackBtn, mSeshUpdate1, mSeshUpdate2, mSeshUpdate3, mSeshUpdate4, mSeshUpdate5, mSeshUpdate6, mSeshUpdate7, mSeshUpdate8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_limit);

        //limit holders
        mSesh1 = findViewById(R.id.time12pm);
        mSesh2 = findViewById(R.id.time1pm);
        mSesh3 = findViewById(R.id.time2pm);
        mSesh4 = findViewById(R.id.time3pm);
        mSesh5 = findViewById(R.id.time6pm);
        mSesh6 = findViewById(R.id.time7pm);
        mSesh7 = findViewById(R.id.time8pm);
        mSesh8 = findViewById(R.id.time9pm);



        // button initialization
        mbackBtn = findViewById(R.id.backBtn);
        mSeshUpdate1 = findViewById(R.id.update12pm);
        mSeshUpdate2 = findViewById(R.id.update1pm);
        mSeshUpdate3 = findViewById(R.id.update2pm);
        mSeshUpdate4 = findViewById(R.id.update3pm);
        mSeshUpdate5 = findViewById(R.id.update6pm);
        mSeshUpdate6 = findViewById(R.id.update7pm);
        mSeshUpdate7 = findViewById(R.id.update8pm);
        mSeshUpdate8 = findViewById(R.id.update9pm);


        //get the instance
        fAuth1 = FirebaseAuth.getInstance();

        //collecting data
        globalLimit = new bookingLimitGlobals();
        //place it in here
        reff = FirebaseDatabase.getInstance().getReference().child("TableLimit");
        reff1 = reff.child("12pm").child("Limit");
        reff2 = reff.child("1pm").child("Limit");
        reff3 = reff.child("2pm").child("Limit");
        reff4 = reff.child("3pm").child("Limit");
        reff5 = reff.child("6pm").child("Limit");
        reff6 = reff.child("7pm").child("Limit");
        reff7 = reff.child("8pm").child("Limit");
        reff8 = reff.child("9pm").child("Limit");

        //if user is null return to login
        if(fAuth1.getCurrentUser() == null) {
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        }
        mbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), staffmain.class));
            }
        });

        //12pm limiter updater
        mSeshUpdate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String limit = mSesh1.getText().toString().trim();

                if(limit.isEmpty()) {
                    mSesh1.setError("Please enter a Limit for tables.");
                    return;
                } else {

                    reff1.setValue(limit);
                    Toast.makeText(tableLimit.this, "Table limit for 12pm Updated!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        //1pm limiter updater
        mSeshUpdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String limit = mSesh2.getText().toString().trim();

                if(limit.isEmpty()) {
                    mSesh2.setError("Please enter a Limit for tables.");
                    return;
                } else {

                    reff2.setValue(limit);
                    Toast.makeText(tableLimit.this, "Table limit for 1pm Updated!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        //2pm limiter updater
        mSeshUpdate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String limit = mSesh3.getText().toString().trim();

                if(limit.isEmpty()) {
                    mSesh3.setError("Please enter a Limit for tables.");
                    return;
                } else {

                    reff3.setValue(limit);
                    Toast.makeText(tableLimit.this, "Table limit for 2pm Updated!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        //3pm limiter updater
        mSeshUpdate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String limit = mSesh4.getText().toString().trim();

                if(limit.isEmpty()) {
                    mSesh4.setError("Please enter a Limit for tables.");
                    return;
                } else {

                    reff4.setValue(limit);
                    Toast.makeText(tableLimit.this, "Table limit for 3pm Updated!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        //6pm limiter updater
        mSeshUpdate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String limit = mSesh5.getText().toString().trim();

                if(limit.isEmpty()) {
                    mSesh5.setError("Please enter a Limit for tables.");
                    return;
                } else {

                    reff5.setValue(limit);
                    Toast.makeText(tableLimit.this, "Table limit for 6pm Updated!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        //7pm limiter updater
        mSeshUpdate6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String limit = mSesh6.getText().toString().trim();

                if(limit.isEmpty()) {
                    mSesh6.setError("Please enter a Limit for tables.");
                    return;
                } else {

                    reff6.setValue(limit);
                    Toast.makeText(tableLimit.this, "Table limit for 7pm Updated!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        //8pm limiter updater
        mSeshUpdate7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String limit = mSesh7.getText().toString().trim();

                if(limit.isEmpty()) {
                    mSesh7.setError("Please enter a Limit for tables.");
                    return;
                } else {

                    reff7.setValue(limit);
                    Toast.makeText(tableLimit.this, "Table limit for 8pm Updated!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        //9pm limiter updater
        mSeshUpdate8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String limit = mSesh8.getText().toString().trim();

                if(limit.isEmpty()) {
                    mSesh8.setError("Please enter a Limit for tables.");
                    return;
                } else {

                    reff8.setValue(limit);
                    Toast.makeText(tableLimit.this, "Table limit for 9pm Updated!", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}