package com.example.erestaurant;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    private ArrayList<meals> meallist;
    private AdapterView.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onAddClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = (AdapterView.OnItemClickListener) listener;
    }


    public static class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;
        public Button mAddCartBtn;

        public CardViewHolder(View itemView, final AdapterView.OnItemClickListener listener) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
            mAddCartBtn = itemView.findViewById(R.id.AddItemBtn);

            mAddCartBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onAddClick(position);
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
        CardViewHolder CVH = new CardViewHolder(v, mListener);
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


