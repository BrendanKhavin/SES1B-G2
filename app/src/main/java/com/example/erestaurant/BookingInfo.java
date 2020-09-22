package com.example.erestaurant;

public class BookingInfo {
    private String BookingDate;
    private String BookingTime;
    private Integer Pax;


    public String getBookingDate() {
        return BookingDate;
    }

    public void setBookingDate(String bookingdate) {
        BookingDate = bookingdate;
    }

    public String getBookingTime() {
        return BookingTime;
    }

    public void setBookingTime(String bookingtime) {
        BookingTime = bookingtime;
    }

    public Integer getPax() {
        return Pax;
    }

    public void setPax(Integer pax) {
         Pax= pax;
    }
}
