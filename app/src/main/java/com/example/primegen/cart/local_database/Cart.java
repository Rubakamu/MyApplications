package com.example.primegen.cart.local_database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "MyCart")
public class Cart implements Serializable {
    @PrimaryKey
    @NonNull
    @SerializedName("testprofileID")
    @Expose
    private Integer testprofileID;
    @SerializedName("isTestOrProfile")
    @Expose
    private String isTestOrProfile;
    @SerializedName("testprofileName")
    @Expose
    private String testprofileName;
    @SerializedName("testprofileActualPrice")
    @Expose
    private String testprofileActualPrice;
    @SerializedName("testprofileOfferPrice")
    @Expose
    private String testprofileOfferPrice;
    @SerializedName("testprofileTests")
    @Expose
    private String testprofileTests;
    @SerializedName("testprofileDetails")
    @Expose
    private String testprofileDetails;
    @SerializedName("testprofileImage")
    @Expose
    private String testprofileImage;
    @SerializedName("testprofileCondition")
    @Expose
    private String testprofileCondition;
    @SerializedName("testprofileDepartment")
    @Expose
    private String testprofileDepartment;
    @SerializedName("testprofileCategory")
    @Expose
    private String testprofileCategory;
    @SerializedName("testprofileSortOrder")
    @Expose
    private String testprofileSortOrder;
    @SerializedName("testprofileActive")
    @Expose
    private String testprofileActive;
    @SerializedName("testprofileAddedBy")
    @Expose
    private String testprofileAddedBy;
    @SerializedName("testprofileModifiedBy")
    @Expose
    private String testprofileModifiedBy;
    @SerializedName("testprofileCreatedDate")
    @Expose
    private String testprofileCreatedDate;
    @SerializedName("testprofileModifiedDate")
    @Expose
    private String testprofileModifiedDate;
    @SerializedName("testprofileStatus")
    @Expose
    private String testprofileStatus;
    @SerializedName("testprofileFlag")
    @Expose
    private String testprofileFlag;

    private boolean isExpanded;

    @NonNull
    public Integer getTestprofileID() {
        return testprofileID;
    }

    public void setTestprofileID(@NonNull Integer testprofileID) {
        this.testprofileID = testprofileID;
    }

    public String getIsTestOrProfile() {
        return isTestOrProfile;
    }

    public void setIsTestOrProfile(String isTestOrProfile) {
        this.isTestOrProfile = isTestOrProfile;
    }

    public String getTestprofileName() {
        return testprofileName;
    }

    public void setTestprofileName(String testprofileName) {
        this.testprofileName = testprofileName;
    }

    public String getTestprofileActualPrice() {
        return testprofileActualPrice;
    }

    public void setTestprofileActualPrice(String testprofileActualPrice) {
        this.testprofileActualPrice = testprofileActualPrice;
    }

    public String getTestprofileOfferPrice() {
        return testprofileOfferPrice;
    }

    public void setTestprofileOfferPrice(String testprofileOfferPrice) {
        this.testprofileOfferPrice = testprofileOfferPrice;
    }

    public String getTestprofileTests() {
        return testprofileTests;
    }

    public void setTestprofileTests(String testprofileTests) {
        this.testprofileTests = testprofileTests;
    }

    public String getTestprofileDetails() {
        return testprofileDetails;
    }

    public void setTestprofileDetails(String testprofileDetails) {
        this.testprofileDetails = testprofileDetails;
    }

    public String getTestprofileImage() {
        return testprofileImage;
    }

    public void setTestprofileImage(String testprofileImage) {
        this.testprofileImage = testprofileImage;
    }

    public String getTestprofileCondition() {
        return testprofileCondition;
    }

    public void setTestprofileCondition(String testprofileCondition) {
        this.testprofileCondition = testprofileCondition;
    }

    public String getTestprofileDepartment() {
        return testprofileDepartment;
    }

    public void setTestprofileDepartment(String testprofileDepartment) {
        this.testprofileDepartment = testprofileDepartment;
    }

    public String getTestprofileCategory() {
        return testprofileCategory;
    }

    public void setTestprofileCategory(String testprofileCategory) {
        this.testprofileCategory = testprofileCategory;
    }

    public String getTestprofileSortOrder() {
        return testprofileSortOrder;
    }

    public void setTestprofileSortOrder(String testprofileSortOrder) {
        this.testprofileSortOrder = testprofileSortOrder;
    }

    public String getTestprofileActive() {
        return testprofileActive;
    }

    public void setTestprofileActive(String testprofileActive) {
        this.testprofileActive = testprofileActive;
    }

    public String getTestprofileAddedBy() {
        return testprofileAddedBy;
    }

    public void setTestprofileAddedBy(String testprofileAddedBy) {
        this.testprofileAddedBy = testprofileAddedBy;
    }

    public String getTestprofileModifiedBy() {
        return testprofileModifiedBy;
    }

    public void setTestprofileModifiedBy(String testprofileModifiedBy) {
        this.testprofileModifiedBy = testprofileModifiedBy;
    }

    public String getTestprofileCreatedDate() {
        return testprofileCreatedDate;
    }

    public void setTestprofileCreatedDate(String testprofileCreatedDate) {
        this.testprofileCreatedDate = testprofileCreatedDate;
    }

    public String getTestprofileModifiedDate() {
        return testprofileModifiedDate;
    }

    public void setTestprofileModifiedDate(String testprofileModifiedDate) {
        this.testprofileModifiedDate = testprofileModifiedDate;
    }

    public String getTestprofileStatus() {
        return testprofileStatus;
    }

    public void setTestprofileStatus(String testprofileStatus) {
        this.testprofileStatus = testprofileStatus;
    }

    public String getTestprofileFlag() {
        return testprofileFlag;
    }

    public void setTestprofileFlag(String testprofileFlag) {
        this.testprofileFlag = testprofileFlag;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
