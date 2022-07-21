package com.example.primegen.test;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TestResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("test")
    @Expose
    private List<Test> test = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Test> getTest() {
        return test;
    }

    public void setTest(List<Test> test) {
        this.test = test;
    }

}
