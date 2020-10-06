package com.example.erestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class History extends AppCompatActivity {
    Button modifyBtn, deleteBtn, backBtn;
    TextView bookingDate, bookingTime, bookingSeating, bookingSession,bookingFoodSituation;
    String currentUserID;
    private DatabaseReference reff, reff2, reff3;
    private FirebaseAuth bAuth;
    String mytime, mydate, myLimit;
    int i, mycheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        modifyBtn = findViewById(R.id.modifyBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        backBtn = findViewById(R.id.backBtn);

        bookingDate = (TextView) findViewById(R.id.dateTxt);
        bookingTime = (TextView) findViewById(R.id.timeTxt);
        bookingSeating = (TextView) findViewById(R.id.seatingTxt);
        bookingSession = (TextView) findViewById(R.id.sessionTxt);
        bookingFoodSituation = (TextView) findViewById(R.id.foodReqTxt2);

        bAuth = FirebaseAuth.getInstance();

        if(bAuth.getCurrentUser() == null) {
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        }

        currentUserID = bAuth.getCurrentUser().getUid();
        reff = FirebaseDatabase.getInstance().getReference().child("BookingDetails").child(currentUserID);

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {
                    String bDate = dataSnapshot.child("date").getValue().toString();
                    String bTime = dataSnapshot.child("session").getValue().toString();
                    String bNumPeople = dataSnapshot.child("numPeople").getValue().toString();
                    String bStatus = dataSnapshot.child("status").getValue().toString();
                    String bFoodStat = dataSnapshot.child("foodSTat").getValue().toString();
                    bookingFoodSituation.setText(bFoodStat);
                    bookingDate.setText(bDate);
                    bookingTime.setText(bTime);
                    bookingSeating.setText(bNumPeople);
                    bookingSession.setText(bStatus);
                    mydate = dataSnapshot.child("date").getValue().toString();
                    mytime =dataSnapshot.child("session").getValue().toString();
                    reff2 = FirebaseDatabase.getInstance().getReference().child("Seatings").child(mydate).child(mytime).child("Limit");
                    Toast.makeText(History.this, "my date is " +mydate+ "---mytime is "+mytime, Toast.LENGTH_SHORT).show();
                    deleteBtn.setClickable(true);
                    reff2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists())  {
                                myLimit = dataSnapshot.getValue().toString();
                                Toast.makeText(History.this, "my value is " +myLimit, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                } else {
                   // Toast.makeText(getApplicationContext(), "Booking Not Found", Toast.LENGTH_LONG).show();
                    modifyBtn.setText("Create Booking");
                    deleteBtn.setClickable(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(History.this, "unable", Toast.LENGTH_SHORT).show();
            }
        });

        modifyBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view) {
               startActivity(new Intent(getApplicationContext(), booking.class));
            }
        });

        deleteBtn.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick (View view){


                DatabaseReference deleteRef = FirebaseDatabase.getInstance().getReference().child("BookingDetails").child(currentUserID);
                deleteRef.removeValue();
                bookingDate.setText("");
                bookingTime.setText("");
                bookingSeating.setText("");
                bookingSession.setText("");
                bookingFoodSituation.setText("");

                int myGL = Integer.parseInt(myLimit);
                for (i = 0; i <= 1; i++) {
                    if(mycheck != 1) {
                        int myNewL = myGL + 1;
                        reff2.setValue(myNewL);
                        mycheck = 1;

                    }

                }

                deleteBtn.setClickable(false);




            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        mycheck = 0;
       // i =0;
    }
}