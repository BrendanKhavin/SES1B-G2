package com.example.erestaurant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    private ArrayList<MenuItemCard> mFoodList;

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public CardViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.ItemImage);
            mTextView1 = itemView.findViewById(R.id.FoodLine);
            mTextView2 = itemView.findViewById(R.id.PriceLine);
        }
    }
    public CardAdapter(ArrayList<MenuItemCard> exampleList) {
        mFoodList = exampleList;
    }
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item_card, parent, false);
        CardViewHolder evh = new CardViewHolder(v);
        return evh;
    }
    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        MenuItemCard currentItem = mFoodList.get(position);
        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());
    }
    @Override
    public int getItemCount() {
        return mFoodList.size();
    }
}
