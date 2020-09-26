package com.example.erestaurant;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class OrderInvoice extends AppCompatActivity {
    private static final String TAG = "OrderInvoice";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        Log.d(TAG, "onCreate: Started.");
        ListView mListView = (ListView) findViewById(R.id.listView);

        Meal Steak = new Meal("Steak", 70.0);
        Meal Soup = new Meal("Soup", 10.0);
        Meal Broccoli = new Meal("Broccoli", 50.25);
        Meal HelloGradle = new Meal("HelloGradle", 69.99);

        ArrayList<Meal> orderList = new ArrayList<>();
        orderList.add(Steak);
        orderList.add(Soup);
        orderList.add(Broccoli);
        orderList.add(HelloGradle);

        MealListAdapter adapter = new MealListAdapter(this, R.layout.adapter_view_layout, orderList);
        mListView.setAdapter(adapter);


    }
}
