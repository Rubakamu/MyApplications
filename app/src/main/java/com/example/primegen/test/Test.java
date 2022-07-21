package com.example.primegen.test;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Test implements Parcelable {
    @SerializedName("testprofileID")
    @Expose
    private String testprofileID;
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

    @RequiresApi(api = Build.VERSION_CODES.Q)
    protected Test(Parcel in) {
        testprofileID = in.readString();
        isTestOrProfile = in.readString();
        testprofileName = in.readString();
        testprofileActualPrice = in.readString();
        testprofileOfferPrice = in.readString();
        testprofileTests = in.readString();
        testprofileDetails = in.readString();
        testprofileImage = in.readString();
        testprofileCondition = in.readString();
        testprofileDepartment = in.readString();
        testprofileCategory = in.readString();
        testprofileSortOrder = in.readString();
        testprofileActive = in.readString();
        testprofileAddedBy = in.readString();
        testprofileModifiedBy = in.readString();
        testprofileCreatedDate = in.readString();
        testprofileModifiedDate = in.readString();
        testprofileStatus = in.readString();
        testprofileFlag = in.readString();
        isExpanded = in.readBoolean();
    }

    public static final Creator<Test> CREATOR = new Creator<Test>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public Test createFromParcel(Parcel in) {
            return new Test(in);
        }

        @Override
        public Test[] newArray(int size) {
            return new Test[size];
        }
    };

    public String getTestprofileID() {
        return testprofileID;
    }

    public void setTestprofileID(String testprofileID) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(testprofileID);
        dest.writeString(isTestOrProfile);
        dest.writeString(testprofileName);
        dest.writeString(testprofileActualPrice);
        dest.writeString(testprofileOfferPrice);
        dest.writeString(testprofileTests);
        dest.writeString(testprofileDetails);
        dest.writeString(testprofileImage);
        dest.writeString(testprofileCondition);
        dest.writeString(testprofileDepartment);
        dest.writeString(testprofileCategory);
        dest.writeString(testprofileSortOrder);
        dest.writeString(testprofileActive);
        dest.writeString(testprofileAddedBy);
        dest.writeString(testprofileModifiedBy);
        dest.writeString(testprofileCreatedDate);
        dest.writeString(testprofileModifiedDate);
        dest.writeString(testprofileStatus);
        dest.writeString(testprofileFlag);
        dest.writeBoolean(isExpanded);
    }
}
