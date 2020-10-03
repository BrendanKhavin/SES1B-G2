package com.example.erestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class staffmain extends AppCompatActivity {

    Button mUpdateMenus, mStaffKey;
    DatabaseReference reff;
    FirebaseAuth fAuth;
    String currentUserId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staffmain);

        mUpdateMenus = findViewById(R.id.menuupdateBtn);
        mStaffKey = findViewById(R.id.staffKeyBtn);


        fAuth = FirebaseAuth.getInstance();
        currentUserId = fAuth.getCurrentUser().getUid();
        reff = FirebaseDatabase.getInstance().getReference().child("MemberInfo").child(currentUserId);

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {
                    String myKey = dataSnapshot.child("userKey").getValue().toString();
                    if(!myKey.equals("5399921ZX")) {
                       // mUpdateMenus.setVisibility(View.INVISIBLE);
                        mStaffKey.setVisibility(View.INVISIBLE);
                    } else {
                       // mUpdateMenus.setVisibility(View.VISIBLE);
                        mStaffKey.setVisibility(View.VISIBLE);
                    }
                } else {

                    Toast.makeText(getApplicationContext(), "****NOT FOUND****", Toast.LENGTH_LONG).show();
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(this, databaseError.getCode(), Toast.LENGTH_SHORT.show();
                Toast.makeText(staffmain.this, "unable", Toast.LENGTH_SHORT).show();
            }
        });



        mUpdateMenus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), menuUpdateMain.class));
            }
        });

        mStaffKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), keyGen.class));
            }
        });



    }

    public void logout(View view){ //log out my user and send to login page
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }
}