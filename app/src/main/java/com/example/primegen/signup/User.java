package com.example.primegen.signup;

import com.example.primegen.base_properties.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User extends BaseResponse {

    @SerializedName("customerID")
    @Expose
    private String customerID;
    @SerializedName("customerUsername")
    @Expose
    private String customerUsername;
    @SerializedName("customerTitle")
    @Expose
    private String customerTitle;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("customerLastname")
    @Expose
    private String customerLastname;
    @SerializedName("customerEmail")
    @Expose
    private String customerEmail;
    @SerializedName("customerPhone")
    @Expose
    private String customerPhone;
    @SerializedName("customerAddressline1")
    @Expose
    private String customerAddressline1;
    @SerializedName("customerAddressline2")
    @Expose
    private String customerAddressline2;
    @SerializedName("customerArea")
    @Expose
    private String customerArea;
    @SerializedName("customerLandmark")
    @Expose
    private String customerLandmark;
    @SerializedName("customerCity")
    @Expose
    private String customerCity;
    @SerializedName("customerDistrict")
    @Expose
    private String customerDistrict;
    @SerializedName("customerPincode")
    @Expose
    private String customerPincode;
    @SerializedName("customerState")
    @Expose
    private String customerState;
    @SerializedName("customerCountry")
    @Expose
    private String customerCountry;
    @SerializedName("customerRelationship")
    @Expose
    private String customerRelationship;
    @SerializedName("customerDOB")
    @Expose
    private String customerDOB;
    @SerializedName("customerAge")
    @Expose
    private String customerAge;
    @SerializedName("customerGender")
    @Expose
    private String customerGender;
    @SerializedName("customerBloodGroup")
    @Expose
    private String customerBloodGroup;
    @SerializedName("customerActiveStatus")
    @Expose
    private String customerActiveStatus;
    @SerializedName("customerPassword")
    @Expose
    private String customerPassword;
    @SerializedName("customerOTP")
    @Expose
    private String customerOTP;
    @SerializedName("customerVerified")
    @Expose
    private String customerVerified;
    @SerializedName("customerFrom")
    @Expose
    private String customerFrom;
    @SerializedName("customerCart")
    @Expose
    private String customerCart;
    @SerializedName("customerAddedBy")
    @Expose
    private String customerAddedBy;
    @SerializedName("customerCreatedDate")
    @Expose
    private String customerCreatedDate;
    @SerializedName("customerModifiedDate")
    @Expose
    private String customerModifiedDate;
    @SerializedName("customerStatus")
    @Expose
    private String customerStatus;
    @SerializedName("customerFlag")
    @Expose
    private String customerFlag;

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getCustomerTitle() {
        return customerTitle;
    }

    public void setCustomerTitle(String customerTitle) {
        this.customerTitle = customerTitle;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerLastname() {
        return customerLastname;
    }

    public void setCustomerLastname(String customerLastname) {
        this.customerLastname = customerLastname;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddressline1() {
        return customerAddressline1;
    }

    public void setCustomerAddressline1(String customerAddressline1) {
        this.customerAddressline1 = customerAddressline1;
    }

    public String getCustomerAddressline2() {
        return customerAddressline2;
    }

    public void setCustomerAddressline2(String customerAddressline2) {
        this.customerAddressline2 = customerAddressline2;
    }

    public String getCustomerArea() {
        return customerArea;
    }

    public void setCustomerArea(String customerArea) {
        this.customerArea = customerArea;
    }

    public String getCustomerLandmark() {
        return customerLandmark;
    }

    public void setCustomerLandmark(String customerLandmark) {
        this.customerLandmark = customerLandmark;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerDistrict() {
        return customerDistrict;
    }

    public void setCustomerDistrict(String customerDistrict) {
        this.customerDistrict = customerDistrict;
    }

    public String getCustomerPincode() {
        return customerPincode;
    }

    public void setCustomerPincode(String customerPincode) {
        this.customerPincode = customerPincode;
    }

    public String getCustomerState() {
        return customerState;
    }

    public void setCustomerState(String customerState) {
        this.customerState = customerState;
    }

    public String getCustomerCountry() {
        return customerCountry;
    }

    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    public String getCustomerRelationship() {
        return customerRelationship;
    }

    public void setCustomerRelationship(String customerRelationship) {
        this.customerRelationship = customerRelationship;
    }

    public String getCustomerDOB() {
        return customerDOB;
    }

    public void setCustomerDOB(String customerDOB) {
        this.customerDOB = customerDOB;
    }

    public String getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(String customerAge) {
        this.customerAge = customerAge;
    }

    public String getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(String customerGender) {
        this.customerGender = customerGender;
    }

    public String getCustomerBloodGroup() {
        return customerBloodGroup;
    }

    public void setCustomerBloodGroup(String customerBloodGroup) {
        this.customerBloodGroup = customerBloodGroup;
    }

    public String getCustomerActiveStatus() {
        return customerActiveStatus;
    }

    public void setCustomerActiveStatus(String customerActiveStatus) {
        this.customerActiveStatus = customerActiveStatus;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerOTP() {
        return customerOTP;
    }

    public void setCustomerOTP(String customerOTP) {
        this.customerOTP = customerOTP;
    }

    public String getCustomerVerified() {
        return customerVerified;
    }

    public void setCustomerVerified(String customerVerified) {
        this.customerVerified = customerVerified;
    }

    public String getCustomerFrom() {
        return customerFrom;
    }

    public void setCustomerFrom(String customerFrom) {
        this.customerFrom = customerFrom;
    }

    public String getCustomerCart() {
        return customerCart;
    }

    public void setCustomerCart(String customerCart) {
        this.customerCart = customerCart;
    }

    public String getCustomerAddedBy() {
        return customerAddedBy;
    }

    public void setCustomerAddedBy(String customerAddedBy) {
        this.customerAddedBy = customerAddedBy;
    }

    public String getCustomerCreatedDate() {
        return customerCreatedDate;
    }

    public void setCustomerCreatedDate(String customerCreatedDate) {
        this.customerCreatedDate = customerCreatedDate;
    }

    public String getCustomerModifiedDate() {
        return customerModifiedDate;
    }

    public void setCustomerModifiedDate(String customerModifiedDate) {
        this.customerModifiedDate = customerModifiedDate;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public String getCustomerFlag() {
        return customerFlag;
    }

    public void setCustomerFlag(String customerFlag) {
        this.customerFlag = customerFlag;
    }
}