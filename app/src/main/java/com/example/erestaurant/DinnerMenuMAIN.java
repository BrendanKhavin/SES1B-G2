package com.example.erestaurant;

import android.content.Intent;
import android.os.Bundle;
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

public class DinnerMenuMAIN extends AppCompatActivity {

    private String currentUserId;
    private FirebaseAuth bAuth;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    // private FirebaseDatabase fBase;
    private DatabaseReference reffy;
    private DatabaseReference reffy1;
    long count;
    //DatabaseReference reff2;
    FirebaseDatabase db;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinner_menu);

        //current use null check
        bAuth = FirebaseAuth.getInstance();
        currentUserId = bAuth.getCurrentUser().getUid();
        if(bAuth.getCurrentUser() == null) {
            startActivity(new Intent(getApplicationContext(),Login.class));
            finish();
        }


        /*
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
                        //get the count value for the lunch meals data
                     count = dataSnapshot.getChildrenCount();
                        //set lunch meal list
                        ArrayList<meals> meallist = new ArrayList<>();
                            for (long i = 1; i <= count; i++) {
                                String foodname = dataSnapshot.child("foodName").getValue().toString();
                                String foodprice = dataSnapshot.child("foodPrice").getValue().toString();
                                meallist.add(new meals(foodname, foodprice));

                                mRecyclerView = findViewById(R.id.recyclerView);
                                mRecyclerView.setHasFixedSize(true);
                                mLayoutManager = new LinearLayoutManager(LunchMenu.this);
                                mAdapter = new CardAdapter(meallist);
                                mRecyclerView.setLayoutManager(mLayoutManager);
                                mRecyclerView.setAdapter(mAdapter);
         */
        final ArrayList<meals> meallist = new ArrayList<>();
        reffy = FirebaseDatabase.getInstance().getReference().child("meals").child("Dinner");
        reffy.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {
                    //get the count value for the lunch meals data
                    count = dataSnapshot.getChildrenCount();
                    for(int i = 1; i <= count; i++) {
                        String mealinfo = "fMeal" + i;
                        reffy1 = FirebaseDatabase.getInstance().getReference().child("meals").child("Dinner").child(mealinfo);
                        reffy1.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                if(dataSnapshot.exists()) {

                                    String foodname = dataSnapshot.child("foodName").getValue().toString();
                                    String foodprice = dataSnapshot.child("foodPrice").getValue().toString();
                                    meallist.add(new meals(foodname,"Price: $" + foodprice));

                                    mRecyclerView = findViewById(R.id.recyclerView);
                                    mRecyclerView.setHasFixedSize(true);
                                    mLayoutManager = new LinearLayoutManager(DinnerMenuMAIN.this);
                                    mAdapter = new CardAdapterMAIN(meallist);
                                    mRecyclerView.setLayoutManager(mLayoutManager);
                                    mRecyclerView.setAdapter(mAdapter);
                                } else {

                                    Toast.makeText(getApplicationContext(), "****NOT FOUND****", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                //Toast.makeText(this, databaseError.getCode(), Toast.LENGTH_SHORT.show();
                                Toast.makeText(DinnerMenuMAIN.this, "unable", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "****NOT FOUND****", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(this, databaseError.getCode(), Toast.LENGTH_SHORT.show();
                Toast.makeText(DinnerMenuMAIN.this, "unable", Toast.LENGTH_SHORT).show();
            }
        });

    }


}