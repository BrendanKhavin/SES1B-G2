package com.example.erestaurant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    FirebaseAuth fAuth1;
    DatabaseReference reff4, reff5;
    String currentUserId;

    private ArrayList<meals> meallist;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;
        public ImageView mAddToCart;

        public CardViewHolder(View mealcard, final OnItemClickListener listener) {
            super(mealcard);
            mTextView1 = mealcard.findViewById(R.id.textView);
            mTextView2 = mealcard.findViewById(R.id.textView2);
            mAddToCart = mealcard.findViewById(R.id.AddItem);

            mAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
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
        CardViewHolder CVH = new CardViewHolder(v,mListener);
        fAuth1 = FirebaseAuth.getInstance();
        currentUserId= fAuth1.getCurrentUser().getUid();
        reff4 = FirebaseDatabase.getInstance().getReference().child("ShopTemp").child(currentUserId);
        return CVH;
    }
    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        meals currentItem = meallist.get(position);
        int temp = position;
        holder.mTextView1.setText(currentItem.getFoodName());
        holder.mTextView2.setText(currentItem.getFoodPrice());





        //holder.mAddToCart.setOnClickListener(new View.OnClickListener(){
        //      @Override
        //      public void onClick(View v) {
        //           reff4.child("Food").child("Food").setValue("1");}});

    }
    @Override
    public int getItemCount() {
        return meallist.size();
    }
}




