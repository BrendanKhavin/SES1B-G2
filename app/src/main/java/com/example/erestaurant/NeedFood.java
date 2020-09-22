package com.example.erestaurant;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NeedFood extends AppCompatActivity {

    Button mYesBtn, mNoBtn,mBackBtn;
    RadioGroup radioGroup,radioGroup2, radioGroup3, radioGroup4;

    //database stuff
    BookingDetails booking;
    //FirebaseAuth fAuth;
    FirebaseAuth fAuth1;
    //collecting data
    DatabaseReference reff4, reff5;
    //get user id
    String currentUserId, status;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needfood);

        fAuth1 = FirebaseAuth.getInstance();
        if(fAuth1.getCurrentUser() == null) {
            startActivity(new Intent(getApplicationContext(),Login.class));
            finish();
        }
        //get user id
        currentUserId= fAuth1.getCurrentUser().getUid();
        //go to users booking branch
        reff4 = FirebaseDatabase.getInstance().getReference().child("BookingDetails").child(currentUserId);

        mYesBtn = findViewById(R.id.YesBtn);
        mNoBtn = findViewById(R.id.NoBtn);



        //get session status for booking
        reff4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {
                    status = dataSnapshot.child("status").getValue().toString();
                } else {

                    Toast.makeText(getApplicationContext(), "****NOT FOUND****", Toast.LENGTH_LONG).show();
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(this, databaseError.getCode(), Toast.LENGTH_SHORT.show();
                Toast.makeText(NeedFood.this, "unable", Toast.LENGTH_SHORT).show();
            }
        });



        mYesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(status.equals("Lunch")) {
                    startActivity(new Intent(getApplicationContext(),LunchMenu.class));
                } else {
                    startActivity(new Intent(getApplicationContext(),DinnerMenu.class));
                }
               // reff4 = FirebaseDatabase.getInstance().getReference().child("BookingDetails").child();
            }
        });


        mNoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }
}