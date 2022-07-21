package com.example.primegen.signup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("customerID")
    @Expose
    private Integer customerID;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

}
