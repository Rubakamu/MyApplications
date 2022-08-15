package com.example.primegen.profile.family;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FamilyResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("family")
    @Expose
    private List<Family> family = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Family> getFamily() {
        return family;
    }

    public void setFamily(List<Family> family) {
        this.family = family;
    }
}
