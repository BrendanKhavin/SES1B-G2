package com.example.erestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class booking extends AppCompatActivity {
    public static TextView dateTextView;
   // public static TextView timeTextView;
    Button mBackBtn;
    //FirebaseAuth fAuth;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        mBackBtn = findViewById(R.id.backBtn);
        //timeTextView = findViewById(R.id.timeTextView);
        dateTextView = findViewById(R.id.dateTextView);

        radioGroup = findViewById(R.id.radioGroup);
        textView = findViewById(R.id.text_view_selected);

        Button buttonApply = findViewById(R.id.button_apply);
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                textView.setText("Your choice: " + radioButton.getText());
            }
        });

      //  fAuth = FirebaseAuth.getInstance();

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }

    //Date and Time Picker
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
    /*
    public static void SetTimeText(int hour, int minute) {
        String amPm;
        if (hour >= 12) {
            amPm = " PM";
        } else
            amPm = " AM";
        timeTextView.setText(String.format("%02d:%02d", hour, minute) + amPm);
    }

    public void btn_PickerTime(View view) {
        DialogFragment fragment = new TimePickerFragment();
        fragment.show(getSupportFragmentManager(), "time picker");
    }
     */

    public void backBtnPressed(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));

    }
}