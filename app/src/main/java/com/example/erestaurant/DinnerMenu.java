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

public class DinnerMenu extends AppCompatActivity {

    private String currentUserId;
    private FirebaseAuth bAuth;
    private RecyclerView mRecyclerView;
    private CardAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    int x = 0;

    Button mContinueBtn;
    Button mAddToCart;

    // private FirebaseDatabase fBase;
    private DatabaseReference reffy,reffy1,reffy2;
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

            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        }

        mContinueBtn = findViewById(R.id.goBackBtn);

        mContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Checkout.class));
                // reff4 = FirebaseDatabase.getInstance().getReference().child("BookingDetails").child();
            }
        });




        final ArrayList<meals> meallist = new ArrayList<>();
        reffy = FirebaseDatabase.getInstance().getReference().child("meals").child("Lunch");
        reffy2 = FirebaseDatabase.getInstance().getReference().child("ShopTemp").child(currentUserId);
        reffy.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {
                    //get the count value for the lunch meals data
                    count = dataSnapshot.getChildrenCount();
                    for(int i = 1; i <= count; i++) {
                        String mealinfo = "fMeal" + i;
                        reffy1 = FirebaseDatabase.getInstance().getReference().child("meals").child("Lunch").child(mealinfo);
                        reffy1.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                if(dataSnapshot.exists()) {

                                    String foodname = dataSnapshot.child("foodName").getValue().toString();
                                    String foodprice = dataSnapshot.child("foodPrice").getValue().toString();
                                    meallist.add(new meals(foodname,"Price: $" + foodprice));

                                    mRecyclerView = findViewById(R.id.recyclerView);
                                    mRecyclerView.setHasFixedSize(true);
                                    mLayoutManager = new LinearLayoutManager(DinnerMenu.this);
                                    mAdapter = new CardAdapter(meallist);
                                    mRecyclerView.setLayoutManager(mLayoutManager);
                                    mRecyclerView.setAdapter(mAdapter);

                                    mAdapter.setOnItemClickListener(new CardAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(int position) {
                                            reffy2.child("FoodItem").child("Item"+String.valueOf(x)).setValue(meallist.get(position));
                                            x++;
                                            reffy2.child("FoodCount").setValue(x);
                                        }
                                    });

                                } else {

                                    mAdapter.setOnItemClickListener(new CardAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(int position) {
                                            reffy2.child("FoodItem").child("Item"+String.valueOf(x)).setValue(meallist.get(position));
                                            x++;
                                        }
                                    });

                                }  // Toast.makeText(getApplicationContext(), "****NOT FOUND****", Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                //Toast.makeText(this, databaseError.getCode(), Toast.LENGTH_SHORT.show();
                               //Toast.makeText(DinnerMenu.this, "unable", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }  // Toast.makeText(getApplicationContext(), "****NOT FOUND****", Toast.LENGTH_LONG).show();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(this, databaseError.getCode(), Toast.LENGTH_SHORT.show();
                //Toast.makeText(DinnerMenu.this, "unable", Toast.LENGTH_SHORT).show();
            }
        });

    }


}