package com.example.erestaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {

    Button mBack2Btn;
    //new
    TextView mFullName, mAge, mFavFood, mEmail;
    //FirebaseUser user;
    //String uid;
    FirebaseAuth fAuth;
    //DatabaseReference reff2;
    //end

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        fAuth = FirebaseAuth.getInstance();

        mBack2Btn = findViewById(R.id.back2Btn);


        //  fAuth = FirebaseAuth.getInstance();

        mBack2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        mFullName = findViewById(R.id.zfullname);
        mAge = findViewById(R.id.zAge);
        mEmail = findViewById(R.id.zEmail);
        mFavFood = findViewById(R.id.zFaveFood);


        //end

        if(fAuth.getCurrentUser() == null) {
            startActivity(new Intent(getApplicationContext(),Login.class));
            finish();
        }
    }


}