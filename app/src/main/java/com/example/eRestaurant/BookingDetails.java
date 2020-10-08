package com.example.eRestaurant;

public class BookingDetails {
    private String bookingID;
    private String numPeople;
    private String Date;
    private String Session;
    private String Status;
    private String FoodSTat;


    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(String numPeople) {
        this.numPeople = numPeople;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getSession() {
        return Session;
    }

    public void setSession(String session) {
        Session = session;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getFoodSTat() {
        return FoodSTat;
    }

    public void setFoodSTat(String food) {
        FoodSTat = food;
    }


}

