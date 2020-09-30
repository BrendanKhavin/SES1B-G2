package com.example.erestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class booking extends AppCompatActivity {
    public static TextView dateTextView;
    Button mBackBtn, mconfirmBtn;
    RadioGroup radioGroup,radioGroup2, radioGroup3, radioGroup4;
    RadioButton radioButton, radioButton2, radioButton3, radioButton4;
    TextView textView;
    String ButtonP, currentUserId, name, test, testid;


    //database stuff
    BookingDetails booking;
    //FirebaseAuth fAuth;
    FirebaseAuth fAuth1;
    //collecting data
    DatabaseReference reff4, reff5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup2 = findViewById(R.id.radioGroup2);
        radioGroup3 = findViewById(R.id.radioGroup3);
        radioGroup4 = findViewById(R.id.radioGroup4);

        int radioId = radioGroup.getCheckedRadioButtonId();
        int radioId2 = radioGroup2.getCheckedRadioButtonId();
        int radioId3 = radioGroup3.getCheckedRadioButtonId();
        int radioId4 = radioGroup4.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);
        radioButton2 = findViewById(radioId2);
        radioButton3 = findViewById(radioId3);
        radioButton4 = findViewById(radioId4);
        testid = "1";

        mBackBtn = findViewById(R.id.backBtn);
        dateTextView = findViewById(R.id.dateTextView);
        fAuth1 = FirebaseAuth.getInstance();

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        mconfirmBtn = findViewById(R.id.confirmBtn);
        //collecting data
        booking = new BookingDetails();
        reff4 = FirebaseDatabase.getInstance().getReference().child("ShopTemp");

        if(fAuth1.getCurrentUser() == null) {
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        }


        mconfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bookingID = "1";
                String numPpl = radioButton4.getText().toString();
                if(radioGroup2.isShown()){
                    String time = radioButton2.getText().toString();
                    booking.setSession(time);
                } else if(radioGroup3.isShown()) {
                    String time = radioButton3.getText().toString();
                    booking.setSession(time);
                }
                String date = dateTextView.getText().toString();
                String status = radioButton.getText().toString();
                String food = "No";
                booking.setBookingID(bookingID);
                booking.setStatus(status);
                booking.setDate(date);
                booking.setNumPeople(numPpl);
                currentUserId= fAuth1.getCurrentUser().getUid();
                reff5 = FirebaseDatabase.getInstance().getReference().child(currentUserId);



                if(date.equals("")) {
                    Toast.makeText(com.example.erestaurant.booking.this, "Please select a date", Toast.LENGTH_SHORT).show();
                } else {

                    reff4.child(currentUserId).setValue(booking);
                    startActivity(new Intent(getApplicationContext(), NeedFood.class));

                }

            }
        });
    }



    //status updater
    public void checkButton(View v) {

        radioGroup = findViewById(R.id.radioGroup);
        radioGroup2 = findViewById(R.id.radioGroup2);
        radioGroup3 = findViewById(R.id.radioGroup3);
        textView = findViewById(R.id.text_view_selected);

        int radioId = radioGroup.getCheckedRadioButtonId();
        int radioId2 = radioGroup2.getCheckedRadioButtonId();
        int radioId3 = radioGroup3.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);
        radioButton2 = findViewById(radioId2);
        radioButton3 = findViewById(radioId3);




        //int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        ButtonP = radioButton.getText().toString();
        if(ButtonP.equals("Lunch")) {
            radioGroup3.setVisibility(View.INVISIBLE);
            radioGroup2.setVisibility(View.VISIBLE);
            mconfirmBtn.setVisibility(View.VISIBLE);
        } else {
            radioGroup3.setVisibility(View.VISIBLE);
            radioGroup2.setVisibility(View.INVISIBLE);
            mconfirmBtn.setVisibility(View.VISIBLE);
        }
        radioGroup4.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);

    }


    //session time updater
    public void checkButton3(View v) {

        radioGroup2 = findViewById(R.id.radioGroup2);
        radioGroup3 = findViewById(R.id.radioGroup3);

        int radioId2 = radioGroup2.getCheckedRadioButtonId();
        int radioId3 = radioGroup3.getCheckedRadioButtonId();

        radioButton2 = findViewById(radioId2);
        radioButton3 = findViewById(radioId3);

    }


    //num of people updater
    public void checkButton4(View v) {

        radioGroup4 = findViewById(R.id.radioGroup4);


        int radioId4 = radioGroup4.getCheckedRadioButtonId();


        radioButton4 = findViewById(radioId4);

        //int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton4 = findViewById(radioId4);
    }






    //Date and Time Picker
    @Override
    public <T extends View> T findViewById(int id) {
        return super.findViewById(id);
    }
    public static void SetDateText(int year, int month, int day) {
        if(month < 10){
            dateTextView.setText(day + "/0" + month + "/" + year);
        }
        else{
            dateTextView.setText(day + "/" + month + "/" + year);
        }
    }
    public void btn_PickerDate(View view) {
        DialogFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "date picker");
    }


}