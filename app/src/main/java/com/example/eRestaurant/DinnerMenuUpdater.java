package com.example.eRestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DinnerMenuUpdater extends AppCompatActivity {


    //database stuff
    meals mealUpdater;
    //FirebaseAuth fAuth;
    FirebaseAuth fAuth1;
    //collecting data
    DatabaseReference reff;
    //sub branches
    DatabaseReference reff1, reff2, reff3, reff4, reff5, reff6, reff7, reff8, reff9, reff10, reff11, reff12;
    TextView f1tag, f2tag, f3tag, f4tag, f5tag,f6tag,f7tag,f8tag,f9tag,f10tag,f11tag,f12tag;
    EditText fMeal1Name,fMeal1Price, fMeal2Name,fMeal2Price, fMeal3Name,fMeal3Price, fMeal4Name,fMeal4Price, fMeal5Name,fMeal5Price, fMeal6Name,fMeal6Price;
    EditText fMeal1Name2,fMeal1Price2, fMeal2Name2,fMeal2Price2, fMeal3Name2,fMeal3Price2, fMeal4Name2,fMeal4Price2, fMeal5Name2,fMeal5Price2, fMeal6Name2,fMeal6Price2;
    Button mbackBtn, fMeal1UpdateBtn, fMeal2UpdateBtn, fMeal3UpdateBtn ,fMeal4UpdateBtn, fMeal5UpdateBtn, fMeal6UpdateBtn;
    Button mNextBtn,mPrevBtn, fMeal1UpdateBtn2, fMeal2UpdateBtn2, fMeal3UpdateBtn2, fMeal4UpdateBtn2, fMeal5UpdateBtn2, fMeal6UpdateBtn2;
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
        setContentView(R.layout.activity_dinner_menu_updater);

        //tags
        f1tag= findViewById(R.id.textView13);
        f2tag= findViewById(R.id.textView16);
        f3tag= findViewById(R.id.textView17);
        f4tag= findViewById(R.id.textView18);
        f5tag= findViewById(R.id.textView19);
        f6tag= findViewById(R.id.textView20);
        f7tag= findViewById(R.id.textView26);
        f8tag= findViewById(R.id.textView23);
        f9tag= findViewById(R.id.textView24);
        f10tag= findViewById(R.id.textView22);
        f11tag= findViewById(R.id.textView25);
        f12tag= findViewById(R.id.textView21);



        mbackBtn = findViewById(R.id.backBtn);


        mbackBtn.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        startActivity(new Intent(getApplicationContext(), menuUpdateMain.class));
        }
        });

        mPrevBtn = findViewById(R.id.prevBtn);
        mNextBtn =findViewById(R.id.nextBtn);

        //text boxes initialization
        //--- names
        fMeal1Name = findViewById(R.id.meal7name);
        fMeal2Name = findViewById(R.id.meal8name);
        fMeal3Name = findViewById(R.id.meal9name);
        fMeal4Name = findViewById(R.id.meal10name);
        fMeal5Name = findViewById(R.id.meal11name);
        fMeal6Name = findViewById(R.id.meal12name);
        fMeal1Name2 = findViewById(R.id.meal7name2);
        fMeal2Name2 = findViewById(R.id.meal8name2);
        fMeal3Name2 = findViewById(R.id.meal9name2);
        fMeal4Name2 = findViewById(R.id.meal10name2);
        fMeal5Name2 = findViewById(R.id.meal11name2);
        fMeal6Name2 = findViewById(R.id.meal12name2);

        //--- prices
        fMeal1Price = findViewById(R.id.meal7price);
        fMeal2Price = findViewById(R.id.meal8price);
        fMeal3Price = findViewById(R.id.meal9price);
        fMeal4Price = findViewById(R.id.meal10price);
        fMeal5Price = findViewById(R.id.meal11price);
        fMeal6Price = findViewById(R.id.meal12price);
        fMeal1Price2 = findViewById(R.id.meal7price2);
        fMeal2Price2 = findViewById(R.id.meal8price2);
        fMeal3Price2 = findViewById(R.id.meal9price2);
        fMeal4Price2 = findViewById(R.id.meal10price2);
        fMeal5Price2 = findViewById(R.id.meal11price2);
        fMeal6Price2 = findViewById(R.id.meal12price2);

        // button initialization
        mbackBtn = findViewById(R.id.backBtn);

        fMeal1UpdateBtn = findViewById(R.id.meal7update);
        fMeal2UpdateBtn = findViewById(R.id.meal8update);
        fMeal3UpdateBtn = findViewById(R.id.meal9update);
        fMeal4UpdateBtn = findViewById(R.id.meal10update);
        fMeal5UpdateBtn = findViewById(R.id.meal11update);
        fMeal6UpdateBtn = findViewById(R.id.meal12update);
        fMeal1UpdateBtn2 = findViewById(R.id.meal7update2);
        fMeal2UpdateBtn2 = findViewById(R.id.meal8update2);
        fMeal3UpdateBtn2 = findViewById(R.id.meal9update2);
        fMeal4UpdateBtn2 = findViewById(R.id.meal10update2);
        fMeal5UpdateBtn2 = findViewById(R.id.meal11update2);
        fMeal6UpdateBtn2 = findViewById(R.id.meal12update2);


        //get the instance
        fAuth1 = FirebaseAuth.getInstance();

        //collecting data
        mealUpdater = new meals();
        //place it in here
        reff = FirebaseDatabase.getInstance().getReference().child("meals").child("Dinner");

        //if user is null return to login
        if(fAuth1.getCurrentUser() == null) {
            startActivity(new Intent(getApplicationContext(), Login.class));
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
                } else if(price1.isEmpty()) {
                    fMeal1Price.setError("Please enter a valid price for the Dish.");
                    return;
                } else {
                    reff1 = reff.child("fMeal1");
                    mealUpdater.setFoodName(Foodname1);
                    mealUpdater.setFoodPrice(price1);
                    reff1.setValue(mealUpdater);
                    Toast.makeText(DinnerMenuUpdater.this, "Meal 1 Updated!", Toast.LENGTH_SHORT).show();
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
                } else if(price2.isEmpty()) {
                    fMeal2Price.setError("Please enter a valid price for the Dish.");
                    return;
                } else {
                    reff2 = reff.child("fMeal2");
                    mealUpdater.setFoodName(Foodname2);
                    mealUpdater.setFoodPrice(price2);
                    reff2.setValue(mealUpdater);
                    Toast.makeText(DinnerMenuUpdater.this, "Meal 2 Updated!", Toast.LENGTH_SHORT).show();
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
                } else if(price3.isEmpty()) {
                    fMeal3Price.setError("Please enter a valid price for the Dish.");
                    return;
                } else {
                    reff3 = reff.child("fMeal3");
                    mealUpdater.setFoodName(Foodname3);
                    mealUpdater.setFoodPrice(price3);
                    reff3.setValue(mealUpdater);
                    Toast.makeText(DinnerMenuUpdater.this, "Meal 3 Updated!", Toast.LENGTH_SHORT).show();
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
                } else if(price4.isEmpty()) {
                    fMeal4Price.setError("Please enter a valid price for the Dish.");
                    return;
                } else {
                    reff4 = reff.child("fMeal4");
                    mealUpdater.setFoodName(Foodname4);
                    mealUpdater.setFoodPrice(price4);
                    reff4.setValue(mealUpdater);
                    Toast.makeText(DinnerMenuUpdater.this, "Meal 4 Updated!", Toast.LENGTH_SHORT).show();
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
                } else if(price5.isEmpty()) {
                    fMeal5Price.setError("Please enter a valid price for the Dish.");
                    return;
                } else {
                    reff5 = reff.child("fMeal5");
                    mealUpdater.setFoodName(Foodname5);
                    mealUpdater.setFoodPrice(price5);
                    reff5.setValue(mealUpdater);
                    Toast.makeText(DinnerMenuUpdater.this, "Meal 5 Updated!", Toast.LENGTH_SHORT).show();
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
                } else if(price6.isEmpty()) {
                    fMeal6Price.setError("Please enter a valid price for the Dish.");
                    return;
                } else {
                    reff6 = reff.child("fMeal6");
                    mealUpdater.setFoodName(Foodname6);
                    mealUpdater.setFoodPrice(price6);
                    reff6.setValue(mealUpdater);
                    Toast.makeText(DinnerMenuUpdater.this, "Meal 6 Updated!", Toast.LENGTH_SHORT).show();
                }


            }
        });


        //meal 7-12
        //meal 7 updater
        fMeal1UpdateBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Foodname7 = fMeal1Name2.getText().toString().trim();
                String price7 = fMeal1Price2.getText().toString().trim();

                if(TextUtils.isEmpty(Foodname7) || myIsDigitsOnly(Foodname7)) {
                    fMeal1Name2.setError("Please enter a name for the Dish.");
                    return;
                } else if(price7.isEmpty()) {
                    fMeal1Price2.setError("Please enter a valid price for the Dish.");
                    return;
                } else {
                    reff7 = reff.child("fMeal7");
                    mealUpdater.setFoodName(Foodname7);
                    mealUpdater.setFoodPrice(price7);
                    reff7.setValue(mealUpdater);
                    Toast.makeText(DinnerMenuUpdater.this, "Meal 7 Updated!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        //meal 8 updater
        fMeal2UpdateBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Foodname8 = fMeal2Name2.getText().toString().trim();
                String price8 = fMeal2Price2.getText().toString().trim();

                if(TextUtils.isEmpty(Foodname8) || myIsDigitsOnly(Foodname8)) {
                    fMeal2Name2.setError("Please enter a name for the Dish.");
                    return;
                } else if(price8.isEmpty()) {
                    fMeal2Price2.setError("Please enter a valid price for the Dish.");
                    return;
                } else {
                    reff8 = reff.child("fMeal8");
                    mealUpdater.setFoodName(Foodname8);
                    mealUpdater.setFoodPrice(price8);
                    reff8.setValue(mealUpdater);
                    Toast.makeText(DinnerMenuUpdater.this, "Meal 8 Updated!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        //meal 9 updater
        fMeal3UpdateBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Foodname9 = fMeal3Name2.getText().toString().trim();
                String price9 = fMeal3Price2.getText().toString().trim();

                if(TextUtils.isEmpty(Foodname9) || myIsDigitsOnly(Foodname9)) {
                    fMeal3Name2.setError("Please enter a name for the Dish.");
                    return;
                } else if(price9.isEmpty()) {
                    fMeal3Price2.setError("Please enter a valid price for the Dish.");
                    return;
                } else {
                    reff9 = reff.child("fMeal9");
                    mealUpdater.setFoodName(Foodname9);
                    mealUpdater.setFoodPrice(price9);
                    reff9.setValue(mealUpdater);
                    Toast.makeText(DinnerMenuUpdater.this, "Meal 9 Updated!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        //meal 10 updater
        fMeal4UpdateBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Foodname10 = fMeal4Name2.getText().toString().trim();
                String price10= fMeal4Price2.getText().toString().trim();

                if(TextUtils.isEmpty(Foodname10) || myIsDigitsOnly(Foodname10)) {
                    fMeal4Name2.setError("Please enter a name for the Dish.");
                    return;
                } else if(price10.isEmpty()) {
                    fMeal4Price2.setError("Please enter a valid price for the Dish.");
                    return;
                } else {
                    reff10 = reff.child("fMeal10");
                    mealUpdater.setFoodName(Foodname10);
                    mealUpdater.setFoodPrice(price10);
                    reff10.setValue(mealUpdater);
                    Toast.makeText(DinnerMenuUpdater.this, "Meal 10 Updated!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        //meal 11 updater
        fMeal5UpdateBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Foodname11 = fMeal5Name2.getText().toString().trim();
                String price11 = fMeal5Price2.getText().toString().trim();

                if(TextUtils.isEmpty(Foodname11) || myIsDigitsOnly(Foodname11)) {
                    fMeal5Name2.setError("Please enter a name for the Dish.");
                    return;
                } else if(price11.isEmpty()) {
                    fMeal5Price2.setError("Please enter a valid price for the Dish.");
                    return;
                } else {
                    reff11 = reff.child("fMeal11");
                    mealUpdater.setFoodName(Foodname11);
                    mealUpdater.setFoodPrice(price11);
                    reff11.setValue(mealUpdater);
                    Toast.makeText(DinnerMenuUpdater.this, "Meal 11 Updated!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        //meal 12 updater
        fMeal6UpdateBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Foodname12 = fMeal6Name2.getText().toString().trim();
                String price12 = fMeal6Price2.getText().toString().trim();

                if(TextUtils.isEmpty(Foodname12) || myIsDigitsOnly(Foodname12)) {
                    fMeal6Name2.setError("Please enter a name for the Dish.");
                    return;
                } else if(price12.isEmpty()) {
                    fMeal6Price2.setError("Please enter a valid price for the Dish.");
                    return;
                } else {
                    reff12 = reff.child("fMeal12");
                    mealUpdater.setFoodName(Foodname12);
                    mealUpdater.setFoodPrice(price12);
                    reff12.setValue(mealUpdater);
                    Toast.makeText(DinnerMenuUpdater.this, "Meal 12 Updated!", Toast.LENGTH_SHORT).show();
                }


            }
        });


        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mNextBtn.setVisibility(View.INVISIBLE);
                mPrevBtn.setVisibility(View.VISIBLE);
                //tags
                f1tag.setVisibility(View.INVISIBLE);
                f2tag.setVisibility(View.INVISIBLE);
                f3tag.setVisibility(View.INVISIBLE);
                f4tag.setVisibility(View.INVISIBLE);
                f5tag.setVisibility(View.INVISIBLE);
                f6tag.setVisibility(View.INVISIBLE);
                f7tag.setVisibility(View.VISIBLE);
                f8tag.setVisibility(View.VISIBLE);
                f9tag.setVisibility(View.VISIBLE);
                f10tag.setVisibility(View.VISIBLE);
                f11tag.setVisibility(View.VISIBLE);
                f12tag.setVisibility(View.VISIBLE);


                //names
                fMeal1Name.setVisibility(View.INVISIBLE);
                fMeal2Name.setVisibility(View.INVISIBLE);
                fMeal3Name.setVisibility(View.INVISIBLE);
                fMeal4Name.setVisibility(View.INVISIBLE);
                fMeal5Name.setVisibility(View.INVISIBLE);
                fMeal6Name.setVisibility(View.INVISIBLE);
                fMeal1Name2.setVisibility(View.VISIBLE);
                fMeal2Name2.setVisibility(View.VISIBLE);
                fMeal3Name2.setVisibility(View.VISIBLE);
                fMeal4Name2.setVisibility(View.VISIBLE);
                fMeal5Name2.setVisibility(View.VISIBLE);
                fMeal6Name2.setVisibility(View.VISIBLE);
                //prices
                fMeal1Price.setVisibility(View.INVISIBLE);
                fMeal2Price.setVisibility(View.INVISIBLE);
                fMeal3Price.setVisibility(View.INVISIBLE);
                fMeal4Price.setVisibility(View.INVISIBLE);
                fMeal5Price.setVisibility(View.INVISIBLE);
                fMeal6Price.setVisibility(View.INVISIBLE);
                fMeal1Price2.setVisibility(View.VISIBLE);
                fMeal2Price2.setVisibility(View.VISIBLE);
                fMeal3Price2.setVisibility(View.VISIBLE);
                fMeal4Price2.setVisibility(View.VISIBLE);
                fMeal5Price2.setVisibility(View.VISIBLE);
                fMeal6Price2.setVisibility(View.VISIBLE);

                //updates
                fMeal1UpdateBtn.setVisibility(View.INVISIBLE);
                fMeal2UpdateBtn.setVisibility(View.INVISIBLE);
                fMeal3UpdateBtn.setVisibility(View.INVISIBLE);
                fMeal4UpdateBtn.setVisibility(View.INVISIBLE);
                fMeal5UpdateBtn.setVisibility(View.INVISIBLE);
                fMeal6UpdateBtn.setVisibility(View.INVISIBLE);
                fMeal1UpdateBtn2.setVisibility(View.VISIBLE);
                fMeal2UpdateBtn2.setVisibility(View.VISIBLE);
                fMeal3UpdateBtn2.setVisibility(View.VISIBLE);
                fMeal4UpdateBtn2.setVisibility(View.VISIBLE);
                fMeal5UpdateBtn2.setVisibility(View.VISIBLE);
                fMeal6UpdateBtn2.setVisibility(View.VISIBLE);
            }
        });
        mPrevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mNextBtn.setVisibility(View.VISIBLE);
                mPrevBtn.setVisibility(View.INVISIBLE);
                //tags
                f1tag.setVisibility(View.VISIBLE);
                f2tag.setVisibility(View.VISIBLE);
                f3tag.setVisibility(View.VISIBLE);
                f4tag.setVisibility(View.VISIBLE);
                f5tag.setVisibility(View.VISIBLE);
                f6tag.setVisibility(View.VISIBLE);
                f7tag.setVisibility(View.INVISIBLE);
                f8tag.setVisibility(View.INVISIBLE);
                f9tag.setVisibility(View.INVISIBLE);
                f10tag.setVisibility(View.INVISIBLE);
                f11tag.setVisibility(View.INVISIBLE);
                f12tag.setVisibility(View.INVISIBLE);

                //names
                fMeal1Name.setVisibility(View.VISIBLE);
                fMeal2Name.setVisibility(View.VISIBLE);
                fMeal3Name.setVisibility(View.VISIBLE);
                fMeal4Name.setVisibility(View.VISIBLE);
                fMeal5Name.setVisibility(View.VISIBLE);
                fMeal6Name.setVisibility(View.VISIBLE);
                fMeal1Name2.setVisibility(View.INVISIBLE);
                fMeal2Name2.setVisibility(View.INVISIBLE);
                fMeal3Name2.setVisibility(View.INVISIBLE);
                fMeal4Name2.setVisibility(View.INVISIBLE);
                fMeal5Name2.setVisibility(View.INVISIBLE);
                fMeal6Name2.setVisibility(View.INVISIBLE);

                //prices
                fMeal1Price.setVisibility(View.VISIBLE);
                fMeal2Price.setVisibility(View.VISIBLE);
                fMeal3Price.setVisibility(View.VISIBLE);
                fMeal4Price.setVisibility(View.VISIBLE);
                fMeal5Price.setVisibility(View.VISIBLE);
                fMeal6Price.setVisibility(View.VISIBLE);
                fMeal1Price2.setVisibility(View.INVISIBLE);
                fMeal2Price2.setVisibility(View.INVISIBLE);
                fMeal3Price2.setVisibility(View.INVISIBLE);
                fMeal4Price2.setVisibility(View.INVISIBLE);
                fMeal5Price2.setVisibility(View.INVISIBLE);
                fMeal6Price2.setVisibility(View.INVISIBLE);

                //updates
                fMeal1UpdateBtn.setVisibility(View.VISIBLE);
                fMeal2UpdateBtn.setVisibility(View.VISIBLE);
                fMeal3UpdateBtn.setVisibility(View.VISIBLE);
                fMeal4UpdateBtn.setVisibility(View.VISIBLE);
                fMeal5UpdateBtn.setVisibility(View.VISIBLE);
                fMeal6UpdateBtn.setVisibility(View.VISIBLE);
                fMeal1UpdateBtn2.setVisibility(View.INVISIBLE);
                fMeal2UpdateBtn2.setVisibility(View.INVISIBLE);
                fMeal3UpdateBtn2.setVisibility(View.INVISIBLE);
                fMeal4UpdateBtn2.setVisibility(View.INVISIBLE);
                fMeal5UpdateBtn2.setVisibility(View.INVISIBLE);
                fMeal6UpdateBtn2.setVisibility(View.INVISIBLE);

            }
        });



    }
}