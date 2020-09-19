package com.example.erestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DinnerMenuUpdater extends AppCompatActivity {

    Button mbackBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinner_menu_updater);

        mbackBtn = findViewById(R.id.backBtn);

        mbackBtn.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        startActivity(new Intent(getApplicationContext(),menuUpdateMain.class));
        }
        });

        }
        }