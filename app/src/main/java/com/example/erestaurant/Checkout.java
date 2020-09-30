package com.example.erestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Checkout extends AppCompatActivity {
     private String currentUserId;
     private FirebaseAuth bAuth;
     private RecyclerView mRecyclerView;
     private RecyclerView.Adapter mAdapter;
     private RecyclerView.LayoutManager mLayoutManager;
     Button mContinueBtn;
     TextView bDate, bTime, bSeating, bFoodStat,bTotalAmount;
     float jeff = 69;


     BookingDetails booking;
     long count;
     DatabaseReference reff2,reff4, reff69;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
       bAuth = FirebaseAuth.getInstance();
       currentUserId = bAuth.getCurrentUser().getUid();
       if(bAuth.getCurrentUser() == null) {
          startActivity(new Intent(getApplicationContext(),Login.class));
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

                if(dataSnapshot.exists()) {
                    String s1 = "Date : " + dataSnapshot.child("date").getValue().toString();
                    String s2 = "Time : " + dataSnapshot.child("session").getValue().toString();
                    String s3 = "Table For : " + dataSnapshot.child("numPeople").getValue().toString();
                    String s4 = "Food required? " + dataSnapshot.child("Food").getValue().toString();

                    bDate.setText(s1);
                    bTime.setText(s2);
                    bSeating.setText(s3);
                    bFoodStat.setText(s4);

                    String bookingID = "1";
                    String numPpl = dataSnapshot.child("numPeople").getValue().toString();
                    String time = dataSnapshot.child("session").getValue().toString();
                    String date = dataSnapshot.child("date").getValue().toString();
                    String status = dataSnapshot.child("status").getValue().toString();
                    String food = dataSnapshot.child("Food").getValue().toString();
                    booking.setSession(time);
                    booking.setBookingID(bookingID);
                    booking.setStatus(status);
                    booking.setDate(date);
                    booking.setNumPeople(numPpl);
                    booking.setFoodSTat(food);

                    if(food.equals("Yes")) {
                        //for (int x = 0; x<=100; x++){
                        //    String y = dataSnapshot.child("FoodItem").child("Item"+String.valueOf(x)).getValue().toString();
                        //    int i = Integer.parseInt(y);
                        //    jeff = jeff + i;
                        //}
                        String s5 = "Total amount:  $" + String.valueOf(jeff);
                        bTotalAmount.setText(s5);

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
                // Toast.makeText(Checkout.this, "unable", Toast.LENGTH_SHORT).show();
            }
        });

        mContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reff4.child(currentUserId).setValue(booking);
                DatabaseReference deleteRef = FirebaseDatabase.getInstance().getReference().child("ShopTemp").child(currentUserId);
                deleteRef.removeValue();
                Toast.makeText(Checkout.this, "Booking Placed!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });

    }


}
