package com.example.primegen.base_properties;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BranchResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("branch")
    @Expose
    private List<Branch> branch = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Branch> getBranch() {
        return branch;
    }

    public void setBranch(List<Branch> branch) {
        this.branch = branch;
    }
}
