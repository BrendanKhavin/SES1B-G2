package com.example.erestaurant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {




    private ArrayList<meals> meallist;

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;
        public Button mAddToCart;
        DatabaseReference reffy1 = FirebaseDatabase.getInstance().getReference().child("ShoppingCart");



        public CardViewHolder(View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
            mAddToCart = itemView.findViewById(R.id.AddItemBtn);

            mAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  //  meals currentItem = meallist.get(position);
                    String jeff = "2";
                    reffy1.child("Jeff").setValue(jeff);
                }
            });



        }
    }

    public CardAdapter(ArrayList<meals> mealList) {
        this.meallist = mealList;
    }
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mealcard, parent, false);
        CardViewHolder CVH = new CardViewHolder(v);
        return CVH;
    }
    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        meals currentItem = meallist.get(position);
        holder.mTextView1.setText(currentItem.getFoodName());
        holder.mTextView2.setText(currentItem.getFoodPrice());
    }
    @Override
    public int getItemCount() {
        return meallist.size();
    }








}