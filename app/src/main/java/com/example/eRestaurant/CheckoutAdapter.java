package com.example.eRestaurant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.CardViewHolder>  {
    FirebaseAuth fAuth1;
    DatabaseReference reff4, reff5;
    String currentUserId;

    private ArrayList<meals> meallist;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public static class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;

        public CardViewHolder(View mealcard) {
            super(mealcard);
            mTextView1 = mealcard.findViewById(R.id.textView);
            mTextView2 = mealcard.findViewById(R.id.textView2);
        }
    }

    public CheckoutAdapter(ArrayList<meals> mealList) {
        this.meallist = mealList;
    }

    @Override
    public CheckoutAdapter.CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mealcard_formainscreen, parent, false);
        CheckoutAdapter.CardViewHolder CVH = new CheckoutAdapter.CardViewHolder(v);
        fAuth1 = FirebaseAuth.getInstance();
        currentUserId= fAuth1.getCurrentUser().getUid();
        reff4 = FirebaseDatabase.getInstance().getReference().child("ShopTemp").child(currentUserId);
        return CVH;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        meals currentItem = meallist.get(position);
        int temp = position;
        holder.mTextView1.setText(currentItem.getFoodName());
        holder.mTextView2.setText(currentItem.getFoodPrice());
    }

    @Override
    public int getItemCount() {
        return meallist.size();
    }

}
