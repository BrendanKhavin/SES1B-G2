package com.example.erestaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {

    //private ImageView profilePic; //this is for profile pic

    Button mBack2Btn;
    //new
    private TextView mProfileName, mProfileAge, mProfileFavFood, mProfileEmail;
    private String currentUserId;
    private Button mProfileUpdateBtn;
    //FirebaseUser user;
    //String uid;
    private FirebaseAuth bAuth;
    // private FirebaseDatabase fBase;
    private DatabaseReference reffy;
    DatabaseReference reff2;
    FirebaseDatabase db;
    //end

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mProfileName = (TextView) findViewById(R.id.zFullName);
        mProfileAge = (TextView) findViewById(R.id.zAge);
        mProfileEmail = (TextView) findViewById(R.id.zEmail);
        mProfileFavFood = (TextView) findViewById(R.id.zFavouriteFood);

        bAuth = FirebaseAuth.getInstance();
        currentUserId = bAuth.getCurrentUser().getUid();
        reffy = FirebaseDatabase.getInstance().getReference().child("MemberInfo").child(currentUserId);





        //DatabaseReference reff2 = fBase.getReference(bAuth.getUid());
        mBack2Btn = findViewById(R.id.back2Btn);

        mBack2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        if(bAuth.getCurrentUser() == null) {
            startActivity(new Intent(getApplicationContext(),Login.class));
            finish();
        }


        /*
        if(bAuth.getCurrentUser() == null) {
            startActivity(new Intent(getApplicationContext(),Login.class));
            finish();
        }

        */

        reffy.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {
                    String myName = dataSnapshot.child("fullname").getValue().toString();
                    String myAge = dataSnapshot.child("age").getValue().toString();
                    String myFood = dataSnapshot.child("favFood").getValue().toString();
                    String myEmail = dataSnapshot.child("email").getValue().toString();

                    mProfileName.setText(myName);
                    mProfileAge.setText(myAge);
                    mProfileEmail.setText(myEmail);
                    mProfileFavFood.setText(myFood);
                } else {

                    Toast.makeText(getApplicationContext(), "****NOT FOUND****", Toast.LENGTH_LONG).show();
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(this, databaseError.getCode(), Toast.LENGTH_SHORT.show();
                Toast.makeText(profile.this, "unable", Toast.LENGTH_SHORT).show();
            }
        });


    }


}