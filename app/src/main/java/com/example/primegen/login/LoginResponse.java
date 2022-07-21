package com.example.primegen.login;

import com.example.primegen.base_properties.BaseResponse;
import com.example.primegen.signup.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse extends BaseResponse {

    @SerializedName("user")
    @Expose
    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}