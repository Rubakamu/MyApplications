package com.example.primegen.base_properties;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("cid")
    @Expose
    private Integer cid;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }
}
