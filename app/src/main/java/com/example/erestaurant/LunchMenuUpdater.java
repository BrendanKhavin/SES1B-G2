package com.example.erestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LunchMenuUpdater extends AppCompatActivity {


    //database stuff
    meals mealUpdater;
    //FirebaseAuth fAuth;
    FirebaseAuth fAuth1;
    //collecting data
    DatabaseReference reff;
    //sub branches
    DatabaseReference reff1, reff2, reff3, reff4, reff5, reff6;
    EditText fMeal1Name,fMeal1Price, fMeal2Name,fMeal2Price, fMeal3Name,fMeal3Price, fMeal4Name,fMeal4Price, fMeal5Name,fMeal5Price, fMeal6Name,fMeal6Price;
    Button mbackBtn, fMeal1UpdateBtn, fMeal2UpdateBtn, fMeal3UpdateBtn ,fMeal4UpdateBtn, fMeal5UpdateBtn, fMeal6UpdateBtn;

    //check if digit
    boolean myIsDigitsOnly(String str) {
        if(str.isEmpty()) {
            return false;
        } else {
            return TextUtils.isDigitsOnly(str);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch_menu_updater);

        //text boxes initialization
        //--- names
        fMeal1Name = findViewById(R.id.meal1name);
        fMeal2Name = findViewById(R.id.meal2name);
        fMeal3Name = findViewById(R.id.meal3name);
        fMeal4Name = findViewById(R.id.meal4name);
        fMeal5Name = findViewById(R.id.meal5name);
        fMeal6Name = findViewById(R.id.meal6name);

        //--- prices
        fMeal1Price = findViewById(R.id.meal1price);
        fMeal2Price = findViewById(R.id.meal2price);
        fMeal3Price = findViewById(R.id.meal3price);
        fMeal4Price = findViewById(R.id.meal4price);
        fMeal5Price = findViewById(R.id.meal5price);
        fMeal6Price = findViewById(R.id.meal6price);

        // button initialization
        mbackBtn = findViewById(R.id.backBtn);
        fMeal1UpdateBtn = findViewById(R.id.meal1update);
        fMeal2UpdateBtn = findViewById(R.id.meal2update);
        fMeal3UpdateBtn = findViewById(R.id.meal3update);
        fMeal4UpdateBtn = findViewById(R.id.meal4update);
        fMeal5UpdateBtn = findViewById(R.id.meal5update);
        fMeal6UpdateBtn = findViewById(R.id.meal6update);


        //get the instance
        fAuth1 = FirebaseAuth.getInstance();

        //collecting data
        mealUpdater = new meals();
        //place it in here
        reff = FirebaseDatabase.getInstance().getReference().child("meals").child("Lunch");

        //if user is null return to login
        if(fAuth1.getCurrentUser() == null) {
            startActivity(new Intent(getApplicationContext(),Login.class));
            finish();
        }

        //meal 1 updater
        fMeal1UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Foodname1 = fMeal1Name.getText().toString().trim();
                String price1 = fMeal1Price.getText().toString().trim();

                if(TextUtils.isEmpty(Foodname1) || myIsDigitsOnly(Foodname1)) {
                    fMeal1Name.setError("Please enter a name for the Dish.");
                    return;
                } else if(!myIsDigitsOnly(price1)) {
                    fMeal1Price.setError("Please enter a valid price for the Dish.");
                    return;
                } else {
                    reff1 = reff.child("fMeal1");
                    mealUpdater.setFoodName(Foodname1);
                    mealUpdater.setFoodPrice(price1);
                    reff1.setValue(mealUpdater);
                    Toast.makeText(LunchMenuUpdater.this, "Meal 1 Updated!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        //meal 2 updater
        fMeal2UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Foodname2 = fMeal2Name.getText().toString().trim();
                String price2 = fMeal2Price.getText().toString().trim();

                if(TextUtils.isEmpty(Foodname2) || myIsDigitsOnly(Foodname2)) {
                    fMeal2Name.setError("Please enter a name for the Dish.");
                    return;
                } else if(!myIsDigitsOnly(price2)) {
                    fMeal2Price.setError("Please enter a valid price for the Dish.");
                    return;
                } else {
                    reff2 = reff.child("fMeal2");
                    mealUpdater.setFoodName(Foodname2);
                    mealUpdater.setFoodPrice(price2);
                    reff2.setValue(mealUpdater);
                    Toast.makeText(LunchMenuUpdater.this, "Meal 2 Updated!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        //meal 3 updater
        fMeal3UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Foodname3 = fMeal3Name.getText().toString().trim();
                String price3 = fMeal3Price.getText().toString().trim();

                if(TextUtils.isEmpty(Foodname3) || myIsDigitsOnly(Foodname3)) {
                    fMeal3Name.setError("Please enter a name for the Dish.");
                    return;
                } else if(!myIsDigitsOnly(price3)) {
                    fMeal3Price.setError("Please enter a valid price for the Dish.");
                    return;
                } else {
                    reff3 = reff.child("fMeal3");
                    mealUpdater.setFoodName(Foodname3);
                    mealUpdater.setFoodPrice(price3);
                    reff3.setValue(mealUpdater);
                    Toast.makeText(LunchMenuUpdater.this, "Meal 3 Updated!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        //meal 4 updater
        fMeal4UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Foodname4 = fMeal4Name.getText().toString().trim();
                String price4= fMeal4Price.getText().toString().trim();

                if(TextUtils.isEmpty(Foodname4) || myIsDigitsOnly(Foodname4)) {
                    fMeal4Name.setError("Please enter a name for the Dish.");
                    return;
                } else if(!myIsDigitsOnly(price4)) {
                    fMeal4Price.setError("Please enter a valid price for the Dish.");
                    return;
                } else {
                    reff4 = reff.child("fMeal4");
                    mealUpdater.setFoodName(Foodname4);
                    mealUpdater.setFoodPrice(price4);
                    reff4.setValue(mealUpdater);
                    Toast.makeText(LunchMenuUpdater.this, "Meal 4 Updated!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        //meal 5 updater
        fMeal5UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Foodname5 = fMeal5Name.getText().toString().trim();
                String price5 = fMeal5Price.getText().toString().trim();

                if(TextUtils.isEmpty(Foodname5) || myIsDigitsOnly(Foodname5)) {
                    fMeal5Name.setError("Please enter a name for the Dish.");
                    return;
                } else if(!myIsDigitsOnly(price5)) {
                    fMeal5Price.setError("Please enter a valid price for the Dish.");
                    return;
                } else {
                    reff5 = reff.child("fMeal5");
                    mealUpdater.setFoodName(Foodname5);
                    mealUpdater.setFoodPrice(price5);
                    reff5.setValue(mealUpdater);
                    Toast.makeText(LunchMenuUpdater.this, "Meal 5 Updated!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        //meal 6 updater
        fMeal6UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Foodname6 = fMeal6Name.getText().toString().trim();
                String price6 = fMeal6Price.getText().toString().trim();

                if(TextUtils.isEmpty(Foodname6) || myIsDigitsOnly(Foodname6)) {
                    fMeal6Name.setError("Please enter a name for the Dish.");
                    return;
                } else if(!myIsDigitsOnly(price6)) {
                    fMeal6Price.setError("Please enter a valid price for the Dish.");
                    return;
                } else {
                    reff6 = reff.child("fMeal6");
                    mealUpdater.setFoodName(Foodname6);
                    mealUpdater.setFoodPrice(price6);
                    reff6.setValue(mealUpdater);
                    Toast.makeText(LunchMenuUpdater.this, "Meal 6 Updated!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        mbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),menuUpdateMain.class));
            }
        });
    }
}