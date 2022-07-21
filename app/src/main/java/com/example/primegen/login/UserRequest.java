package com.example.primegen.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserRequest {


    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<User> getResult() {
        return result;
    }

    public void setResult(List<User> result) {
        this.result = result;
    }

    @SerializedName("result")
    @Expose
    private List<User> result = null;

    /**
     * No args constructor for use in serialization
     */
    public UserRequest() {
    }

    /**
     * @param result
     * @param code
     * @param message
     */
    public UserRequest(Integer code, String message, List<User> result) {
        super();
        this.code = code;
        this.message = message;
        this.result = result;
//
//    @SerializedName("message")
//    @Expose
//    private String message;
//    @SerializedName("status")
//    @Expose
//    private String status;
//
//    public List<User> getUser() {
//        return user;
//    }
//
//    public void setUser(List<User> user) {
//        this.user = user;
//    }
//
//    @SerializedName("user")
//    @Expose
//    private List<User> user;
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }

    }
}