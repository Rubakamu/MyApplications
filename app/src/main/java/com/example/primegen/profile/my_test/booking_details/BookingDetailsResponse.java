package com.example.primegen.profile.my_test.booking_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookingDetailsResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("bookingDetails")
    @Expose
    private BookingDetails bookingDetails;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BookingDetails getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(BookingDetails bookingDetails) {
        this.bookingDetails = bookingDetails;
    }

}
