package com.example.erestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
     private String currentUserId;
     private FirebaseAuth bAuth;
     private RecyclerView mRecyclerView;
     private RecyclerView.Adapter mAdapter;
     private RecyclerView.LayoutManager mLayoutManager;
     Button mContinueBtn;
     TextView bDate, bTime, bSeating, bFoodStat,bTotalAmount;
     double totalamt = 0.0;
     BookingDetails booking;
     String GlobalLimits;
     long count;
     int x = 0;
     int i = 0;
     int z = 0;
     int s9000 = 0;
     int listnum = 0;
     int mycheck = 0;
     int mycheck2 = 0;
     ArrayList<meals> mealList = new ArrayList<>();
     DatabaseReference reff2,reff4, reff69, reffy1, reff3, reff5, refftest, refftest2, reffiHateThis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

       bAuth = FirebaseAuth.getInstance();
       currentUserId = bAuth.getCurrentUser().getUid();

       if(bAuth.getCurrentUser() == null) {
          startActivity(new Intent(getApplicationContext(), Login.class));
             finish();
        }
        mContinueBtn = findViewById(R.id.goBackBtn);

       bDate = (TextView) findViewById(R.id.TextView1);
       bTime = (TextView) findViewById(R.id.TextView2);
       bSeating = (TextView) findViewById(R.id.TextView3);
       bFoodStat = (TextView) findViewById(R.id.TextView4);
       bTotalAmount = (TextView) findViewById(R.id.PriceView);

        reff2 = FirebaseDatabase.getInstance().getReference().child("ShopTemp").child(currentUserId);
        reff69 = FirebaseDatabase.getInstance().getReference().child("ShopTemp").child(currentUserId).child("FoodItem");
        booking = new BookingDetails();

        reff4 = FirebaseDatabase.getInstance().getReference().child("BookingDetails");



        reff2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists() && dataSnapshot.child("date").getValue() != null && dataSnapshot.child("session").getValue() != null && dataSnapshot.child("numPeople").getValue() != null && dataSnapshot.child("food").getValue() != null) {


                    String s1 = "Date : " + dataSnapshot.child("date").getValue().toString();
                    String s2 = "Time : " + dataSnapshot.child("session").getValue().toString();
                    String s3 = "Table For : " + dataSnapshot.child("numPeople").getValue().toString();
                    String s4 = "Food required? " + dataSnapshot.child("food").getValue().toString();
                    Toast.makeText(Checkout.this, "you made it!", Toast.LENGTH_SHORT).show();

                    //my changes part 1
                    String mytime = dataSnapshot.child("session").getValue().toString();
                    String myDate = dataSnapshot.child("date").getValue().toString();

                    reff3 = FirebaseDatabase.getInstance().getReference().child("Seatings").child(myDate).child(mytime).child("Limit");
                    reff5 = FirebaseDatabase.getInstance().getReference().child("TableLimit").child(mytime).child("Limit");
                    refftest = reff3;
                    refftest2 = reff4.child(currentUserId);




                    refftest.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()) {
                                String mycurrLim = dataSnapshot.getValue().toString();
                                int mycurrL = Integer.parseInt(mycurrLim);
                                if(mycurrL == 0) {
                                    mContinueBtn.setClickable(false);
                                    Toast.makeText(Checkout.this, "This day is booked out!", Toast.LENGTH_SHORT).show();
                                } else {
                                    mContinueBtn.setClickable(true);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    //end


                    bDate.setText(s1);


                    bTime.setText(s2);
                    bSeating.setText(s3);
                    bFoodStat.setText(s4);









                    String bookingID = "1";
                    String numPpl = dataSnapshot.child("numPeople").getValue().toString();
                    String time = dataSnapshot.child("session").getValue().toString();
                    String date = dataSnapshot.child("date").getValue().toString();
                    String status = dataSnapshot.child("status").getValue().toString();
                    String food = dataSnapshot.child("food").getValue().toString();
                    booking.setSession(time);
                    booking.setBookingID(bookingID);
                    booking.setStatus(status);
                    booking.setDate(date);
                    booking.setNumPeople(numPpl);
                    booking.setFoodSTat(food);


                    if(food.equals("Yes")) {
                        reff69.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists() && s9000!= 1){
                                    s9000 = 1;
                                    count = dataSnapshot.getChildrenCount();
                                    double totalList = 0.0;

                                    for(int i = 0; i < count ; i++){
                                        final String item = "Item" + i;
                                        reffy1 = FirebaseDatabase.getInstance().getReference().child("ShopTemp").child(currentUserId).child("FoodItem").child(item);
                                        reffy1.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if(dataSnapshot.exists()) {
                                                    if(dataSnapshot.child("foodName").getValue() != null) {
                                                        if(dataSnapshot.child("foodPrice").getValue() != null) {
                                                            String foodname = dataSnapshot.child("foodName").getValue().toString();
                                                            String foodprice = dataSnapshot.child("foodPrice").getValue().toString();
                                                            mealList.add(new meals(foodname,foodprice));
                                                        }


                                                    }

                                                }



                                                mRecyclerView = findViewById(R.id.recyclerView);
                                                mRecyclerView.setVisibility(View.VISIBLE);
                                                mRecyclerView.setHasFixedSize(true);
                                                mLayoutManager = new LinearLayoutManager(Checkout.this);
                                                mAdapter = new CheckoutAdapter(mealList);
                                                mRecyclerView.setLayoutManager(mLayoutManager);
                                                mRecyclerView.setAdapter(mAdapter);

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                        //totalamt = mealList.size();

                                    }

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        if(dataSnapshot.child("FoodCount").getValue() != null) {
                            listnum =Integer.parseInt(dataSnapshot.child("FoodCount").getValue().toString());
                        } else {
                            listnum = 0;
                        }

                        for(int j = 0; j < listnum; j++){
                            String jeff = "Item" + String.valueOf(j);
                            String s5 = dataSnapshot.child("FoodItem").child(jeff).child("foodPrice").getValue().toString();
                            int index = s5.indexOf("$");
                            String s51 = s5.substring(index+1);
                            totalamt = totalamt + Double.parseDouble(s51);
                        }
                        String s6 = "Total amount: $" + String.valueOf(totalamt);
                        bTotalAmount.setText(s6);


                    } else {
                        String s5 = " Total amount: No charge " ;
                        bTotalAmount.setText(s5);
                    }
                } else {
                   // Toast.makeText(getApplicationContext(), "Not Found", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Checkout.this, "unable", Toast.LENGTH_SHORT).show();
            }
        });




                mContinueBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        reff4.child(currentUserId).setValue(booking);
                        reff4.child(currentUserId).child("OrderList").setValue(mealList);
                        reff4.child(currentUserId).child("TotalPrice").setValue(String.valueOf(totalamt));
                        DatabaseReference deleteRef = FirebaseDatabase.getInstance().getReference().child("ShopTemp").child(currentUserId);
                        deleteRef.removeValue();
                        refftest = reff3;



                    //my changes part 2

                        reff5.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    final String myLimit = dataSnapshot.getValue().toString().trim();
                                    reff3.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if (!dataSnapshot.exists()) {
                                                reff3.setValue(myLimit);
                                                GlobalLimits = myLimit;

                                            } else {
                                                String mynewLim = dataSnapshot.getValue().toString();
                                                GlobalLimits = mynewLim;


                                                if (dataSnapshot.exists()) {

                                                    String myCurrentLimit = dataSnapshot.getValue().toString();
                                                    int myL = Integer.parseInt(myCurrentLimit);
                                                    int myGL = Integer.parseInt(GlobalLimits);
                                                    int myCl = myGL - 1;
                                                   // Toast.makeText(getApplicationContext(), "global =" + myGL + " curr =" + myL + " check=" + myCl, Toast.LENGTH_LONG).show();
                                                    for (i = 0; i <= 1; i++) {
                                                        if (myCl <= myGL && myCl > 1 && mycheck != 1) {
                                                            myL = myCl;
                                                            refftest.setValue(myL);
                                                            mycheck = 1;
                                                        } else if (myCl == 1 && mycheck != 1) {
                                                            refftest.setValue(myCl);
                                                            mycheck = 1;
                                                            mycheck2 = 1;
                                                        } else if (myCl == 0 && mycheck2 != 1) {
                                                            refftest.setValue(myCl);
                                                            mycheck = 1;
                                                            mycheck2 = 1;
                                                        }

                                                    }

                                                }

                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        //end

                        Toast.makeText(Checkout.this, "Booking Placed!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                    }
                });
        i = 0;
        z = 0;
    }


}
