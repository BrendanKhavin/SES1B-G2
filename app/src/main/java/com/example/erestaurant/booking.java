package com.example.erestaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class booking extends AppCompatActivity {
    public static TextView dateTextView;
    Button mBackBtn;
    RadioGroup radioGroup,radioGroup2, radioGroup3;
    RadioButton radioButton, radioButton2, radioButton3;
    TextView textView;
    String ButtonP, test;
    //FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        mBackBtn = findViewById(R.id.backBtn);
        dateTextView = findViewById(R.id.dateTextView);
      //  fAuth = FirebaseAuth.getInstance();

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });


    }



    //test
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
            textView.setText("Your choice: " + radioButton2.getText());
        } else {
            radioGroup3.setVisibility(View.VISIBLE);
            radioGroup2.setVisibility(View.INVISIBLE);
            textView.setText("Your choice: " + radioButton3.getText());

        }
        Toast.makeText(this, "Selected Radio Button: " + radioButton.getText(),
                Toast.LENGTH_SHORT).show();
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