package com.example.erestaurant;

public class MenuItemCard {
    private int mPicture;
    private String mFoodName;
    private String mPrice;
    public MenuItemCard(int imageResource, String text1, String text2) {
        mPicture = imageResource;
        mFoodName = text1;
        mPrice = text2;
    }
    public int getImageResource() {
        return mPicture;
    }
    public String getText1() {
        return mFoodName;
    }
    public String getText2() {
        return mPrice;
    }
}
