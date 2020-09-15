package com.example.erestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
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
    TextView bookingDate, bookingTime, bookingSeating, bookingSession;
    String currentUserID;
    private DatabaseReference reff;
    private FirebaseAuth bAuth;

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

        bAuth = FirebaseAuth.getInstance();
        currentUserID = bAuth.getCurrentUser().getUid();
        reff = FirebaseDatabase.getInstance().getReference().child("BookingDetails").child(currentUserID);

        if(bAuth.getCurrentUser() == null) {
            startActivity(new Intent(getApplicationContext(),Login.class));
            finish();
        }

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {
                    String bDate = dataSnapshot.child("date").getValue().toString();
                    String bTime = dataSnapshot.child("session").getValue().toString();
                    String bNumPeople = dataSnapshot.child("seating").getValue().toString();
                    String bStatus = dataSnapshot.child("status").getValue().toString();

                    bookingDate.setText(bDate);
                    bookingTime.setText(bTime);
                    bookingSeating.setText(bNumPeople);
                    bookingSession.setText(bStatus);
                } else {
                    Toast.makeText(getApplicationContext(), "****NOT FOUND****", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(History.this, "unable", Toast.LENGTH_SHORT).show();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }
}
