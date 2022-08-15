package com.example.primegen.profile.my_address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddressBookResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("addressbook")
    @Expose
    private List<Addressbook> addressbook = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Addressbook> getAddressbook() {
        return addressbook;
    }

    public void setAddressbook(List<Addressbook> addressbook) {
        this.addressbook = addressbook;
    }
}
