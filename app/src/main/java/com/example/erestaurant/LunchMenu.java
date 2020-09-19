package com.example.erestaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LunchMenu extends AppCompatActivity {

    private String currentUserId;
    private FirebaseAuth bAuth;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    // private FirebaseDatabase fBase;
    private DatabaseReference reffy;
    long count;
    //DatabaseReference reff2;
    FirebaseDatabase db;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch_menu);
        /*
        //current use null check
        bAuth = FirebaseAuth.getInstance();
        currentUserId = bAuth.getCurrentUser().getUid();
        if(bAuth.getCurrentUser() == null) {
            startActivity(new Intent(getApplicationContext(),Login.class));
            finish();
        }
        */
        reffy = FirebaseDatabase.getInstance().getReference().child("meals").child("Lunch");
        ArrayList<meals> meallist = new ArrayList<>();
        meallist.add(new meals("curry","23"));
        meallist.add(new meals("soda","11"));
        meallist.add(new meals("salad","12"));
        meallist.add(new meals("curry","23"));
        meallist.add(new meals("soda","11"));
        meallist.add(new meals("salad","12"));
        meallist.add(new meals("curry","23"));
        meallist.add(new meals("soda","11"));
        meallist.add(new meals("salad","12"));


        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new CardAdapter(meallist);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        /*
        //get the count value for the lunch meals data
        reffy.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {
                    count = dataSnapshot.getChildrenCount();
                    //set lunch meal list
                    ArrayList<meals> meallist = new ArrayList<>();
                    for(long i = 0; i < count ; i++){
                        String foodname = dataSnapshot.child("foodName").getValue().toString();
                        String foodprice = dataSnapshot.child("foodPrice").getValue().toString();
                        meallist.add(new meals(foodname,foodprice));
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "****NOT FOUND****", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(this, databaseError.getCode(), Toast.LENGTH_SHORT.show();
                Toast.makeText(LunchMenu.this, "unable", Toast.LENGTH_SHORT).show();
            }
        });

         */



    }


}