package com.example.erestaurant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardAdapterMAIN extends RecyclerView.Adapter<CardAdapterMAIN.CardViewHolder> {
    private ArrayList<meals> meallist;
    public static class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;
        public CardViewHolder(View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
        }
    }
    public CardAdapterMAIN(ArrayList<meals> mealList) {
        this.meallist = mealList;
    }
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mealcard_formainscreen, parent, false);
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