package com.example.primegen.profile.my_test;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookingHistoryResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("bookingHistory")
    @Expose
    private List<BookingHistory> bookingHistory = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<BookingHistory> getBookingHistory() {
        return bookingHistory;
    }

    public void setBookingHistory(List<BookingHistory> bookingHistory) {
        this.bookingHistory = bookingHistory;
    }

}
