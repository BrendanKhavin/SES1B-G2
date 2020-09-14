package com.example.erestaurant.Model;

public class bookingModel {
    private String id;
    private String Date;
    private String Time;
    private String NumPpl;

    public bookingModel(){ }

    public bookingModel(String id, String date, String time, String NumPpl){
        this.id = id;
        Date = date;
        Time = time;
        this.NumPpl = NumPpl;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getNumPpl() {
        return NumPpl;
    }

    public void setNumPpl(String NumPpl) {
        NumPpl = NumPpl;
    }
}
