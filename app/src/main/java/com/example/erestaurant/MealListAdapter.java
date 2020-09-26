package com.example.erestaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.TextView;


import java.util.ArrayList;

public class MealListAdapter extends ArrayAdapter<Meal> {

    private static final String TAG = "MealListAdapter";

    private Context mContext;
    int mResource;

    public MealListAdapter(Context context, int resource, ArrayList<Meal> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String item = getItem(position).getMeal();
        String price = getItem(position).getPrice();

        Meal meal = new Meal(item, price);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvMeal = (TextView) convertView.findViewById(R.id.mealTv);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.priceTv);

        tvMeal.setText(item);
        tvPrice.setText(price);

        return convertView;


    }
}
