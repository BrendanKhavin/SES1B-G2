package com.example.erestaurant;

public class meals {
    private String foodName;
    private String foodPrice;

    public meals(String foodname, String foodprice) {
        this.foodName = foodname;
        this.foodPrice = foodprice;
    }

    public meals() {

    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }
}
