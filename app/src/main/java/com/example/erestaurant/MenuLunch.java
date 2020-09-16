package com.example.erestaurant;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuLunch extends AppCompatActivity {
    private RecyclerView mRecyclerView1,mRecyclerView2,mRecyclerView3,mRecyclerView4,mRecyclerView5;
    private RecyclerView.Adapter mAdapter1,mAdapter2,mAdapter3,mAdapter4,mAdapter5;
    private RecyclerView.LayoutManager mLayoutManager1,mLayoutManager2,mLayoutManager3,mLayoutManager4,mLayoutManager5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_menulunch);


            //Starters
            ArrayList<MenuItemCard> LunchStartersList = new ArrayList<>();
            LunchStartersList.add(new MenuItemCard(R.mipmap.ic_oyster, "Oysters (dosen)", "Price: 32$"));
            LunchStartersList.add(new MenuItemCard(R.mipmap.ic_oyster, "Hummus&Crackers", "Price: 12$"));
            LunchStartersList.add(new MenuItemCard(R.mipmap.ic_oyster, "Fried Mushrooms", "Price: 12$"));
            LunchStartersList.add(new MenuItemCard(R.mipmap.ic_oyster, "Fried Rice", "Price: 10000$"));
            mRecyclerView1 = findViewById(R.id.recyclerViewStarters);
            mRecyclerView1.setHasFixedSize(true);
            mLayoutManager1 = new LinearLayoutManager(this);
            mAdapter1 = new CardAdapter(LunchStartersList);
            mRecyclerView1.setLayoutManager(mLayoutManager1);
            mRecyclerView1.setAdapter(mAdapter1);

            //Mains recyclerViewMains
            ArrayList<MenuItemCard> LunchMainsList = new ArrayList<>();
            LunchMainsList.add(new MenuItemCard(R.mipmap.ic_oyster, "Oysters (dosen)", "Price: 32$"));
            LunchMainsList.add(new MenuItemCard(R.mipmap.ic_oyster, "Hummus&Crackers", "Price: 12$"));
            LunchMainsList.add(new MenuItemCard(R.mipmap.ic_oyster, "Fried Mushrooms", "Price: 12$"));
            LunchMainsList.add(new MenuItemCard(R.mipmap.ic_oyster, "Fried Rice", "Price: 10000$"));
            mRecyclerView2 = findViewById(R.id.recyclerViewMains);
            mRecyclerView2.setHasFixedSize(true);
            mLayoutManager2 = new LinearLayoutManager(this);
            mAdapter2 = new CardAdapter(LunchMainsList);
            mRecyclerView2.setLayoutManager(mLayoutManager2);
            mRecyclerView2.setAdapter(mAdapter2);

            //Sides recyclerViewSides
            ArrayList<MenuItemCard> LunchSidesList = new ArrayList<>();
            LunchSidesList.add(new MenuItemCard(R.mipmap.ic_oyster, "Oysters (dosen)", "Price: 32$"));
            LunchSidesList.add(new MenuItemCard(R.mipmap.ic_oyster, "Hummus&Crackers", "Price: 12$"));
            LunchSidesList.add(new MenuItemCard(R.mipmap.ic_oyster, "Fried Mushrooms", "Price: 12$"));
            LunchSidesList.add(new MenuItemCard(R.mipmap.ic_oyster, "Fried Rice", "Price: 10000$"));
            mRecyclerView3 = findViewById(R.id.recyclerViewSides);
            mRecyclerView3.setHasFixedSize(true);
            mLayoutManager3 = new LinearLayoutManager(this);
            mAdapter3 = new CardAdapter(LunchSidesList);
            mRecyclerView3.setLayoutManager(mLayoutManager3);
            mRecyclerView3.setAdapter(mAdapter3);

            //Desserts recyclerViewDesserts
            ArrayList<MenuItemCard> LunchDessertsList = new ArrayList<>();
            LunchDessertsList.add(new MenuItemCard(R.mipmap.ic_oyster, "Oysters (dosen)", "Price: 32$"));
            LunchDessertsList.add(new MenuItemCard(R.mipmap.ic_oyster, "Hummus&Crackers", "Price: 12$"));
            LunchDessertsList.add(new MenuItemCard(R.mipmap.ic_oyster, "Fried Mushrooms", "Price: 12$"));
            LunchDessertsList.add(new MenuItemCard(R.mipmap.ic_oyster, "Fried Rice", "Price: 10000$"));
            mRecyclerView4 = findViewById(R.id.recyclerViewDesserts);
            mRecyclerView4.setHasFixedSize(true);
            mLayoutManager4 = new LinearLayoutManager(this);
            mAdapter4 = new CardAdapter(LunchDessertsList);
            mRecyclerView4.setLayoutManager(mLayoutManager4);
            mRecyclerView4.setAdapter(mAdapter4);

            //Feature recyclerViewFeature
            ArrayList<MenuItemCard> LunchFeatureList = new ArrayList<>();
            LunchFeatureList.add(new MenuItemCard(R.mipmap.ic_oyster, "Oysters (dosen)", "Price: 32$"));
            LunchFeatureList.add(new MenuItemCard(R.mipmap.ic_oyster, "Hummus&Crackers", "Price: 12$"));
            LunchFeatureList.add(new MenuItemCard(R.mipmap.ic_oyster, "Fried Mushrooms", "Price: 12$"));
            LunchFeatureList.add(new MenuItemCard(R.mipmap.ic_oyster, "Fried Rice", "Price: 10000$"));
            mRecyclerView5 = findViewById(R.id.recyclerViewFeature);
            mRecyclerView5.setHasFixedSize(true);
            mLayoutManager5 = new LinearLayoutManager(this);
            mAdapter5 = new CardAdapter(LunchFeatureList);
            mRecyclerView5.setLayoutManager(mLayoutManager1);
            mRecyclerView5.setAdapter(mAdapter5);

    }
}
