package com.example.erestaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Checkout extends AppCompatActivity {

    Button mConfirmBtn, mBackBtn;

    private TextView mPaxResultTv, mDateTv, mTimeTv, mPriceDisplayTv;
    private ListView mOrderLv;
    private DatabaseReference reff;
    BookingDetails booking;
    FirebaseAuth fAuth;

    private String currentBookingId;

    private FirebaseAuth bAuth;

    private DatabaseReference reffy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        mPaxResultTv = findViewById(R.id.paxResultTv);
        mConfirmBtn = findViewById(R.id.confirmBtn);
        mDateTv = findViewById(R.id.dateTv);
        mTimeTv = findViewById(R.id.timeTv);
        mPriceDisplayTv = findViewById(R.id.priceDisplayTv);
        mBackBtn = findViewById(R.id.backBtn);

        fAuth = FirebaseAuth.getInstance();

        bAuth = FirebaseAuth.getInstance();
        currentBookingId = bAuth.getCurrentUser().getUid();
        reffy = FirebaseDatabase.getInstance().getReference().child("BookingDetails").child(currentBookingId);


        booking = new BookingDetails();
        if(fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        reffy.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {
                    String myDate = dataSnapshot.child("date").getValue().toString();
                    String myPax = dataSnapshot.child("numPeople").getValue().toString();
                    String myTime = dataSnapshot.child("session").getValue().toString();

                    mDateTv.setText(myDate);
                    mPaxResultTv.setText(myPax);
                    mTimeTv.setText(myTime);
                } else {

                    Toast.makeText(getApplicationContext(), "****NOT FOUND****", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(this, databaseError.getCode(), Toast.LENGTH_SHORT.show();
                Toast.makeText(Checkout.this, "unable", Toast.LENGTH_SHORT).show();
            }
        });



    }

}
