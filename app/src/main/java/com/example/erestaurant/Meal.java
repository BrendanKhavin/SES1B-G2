package com.example.erestaurant;

public class Meal {
    private String meal;
    private String price;

    public Meal(String meal, String price){
        this.meal = meal;
        this.price = price;
    }

    public String getMeal(){
        return meal;
    }

    public void setMeal(String meal){
        this.meal = meal;
    }

    public String getPrice(){
        return price;
    }

    public void setPrice(String price){
        this.price = price;
    }
}
