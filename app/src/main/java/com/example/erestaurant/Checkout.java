package com.example.erestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Checkout extends AppCompatActivity {
//    private String currentUserId;
 //   private FirebaseAuth bAuth;
 //   private RecyclerView mRecyclerView;
 //   private RecyclerView.Adapter mAdapter;
 //   private RecyclerView.LayoutManager mLayoutManager;
    Button mContinueBtn;

    // private FirebaseDatabase fBase;
 //   private DatabaseReference reffy;
 //   private DatabaseReference reffy1;
  //  long count;
    //DatabaseReference reff2;
 //   FirebaseDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        //current use null check
    //    bAuth = FirebaseAuth.getInstance();
    //    currentUserId = bAuth.getCurrentUser().getUid();
     //   if(bAuth.getCurrentUser() == null) {
     //       startActivity(new Intent(getApplicationContext(),Login.class));
     //       finish();
     //   }

        mContinueBtn = findViewById(R.id.continueBtn);

        mContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                // reff4 = FirebaseDatabase.getInstance().getReference().child("BookingDetails").child();
            }
        });

    }


}
