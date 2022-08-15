package com.example.primegen.base_properties;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Branch {
    @SerializedName("branchID")
    @Expose
    private String branchID;
    @SerializedName("branchName")
    @Expose
    private String branchName;
    @SerializedName("branchSortBy")
    @Expose
    private String branchSortBy;
    @SerializedName("branchCreatedBy")
    @Expose
    private String branchCreatedBy;
    @SerializedName("branchEdit")
    @Expose
    private String branchEdit;
    @SerializedName("branchCreatedDate")
    @Expose
    private String branchCreatedDate;
    @SerializedName("branchModifiedDate")
    @Expose
    private String branchModifiedDate;
    @SerializedName("branchStatus")
    @Expose
    private String branchStatus;
    @SerializedName("branchFlag")
    @Expose
    private String branchFlag;

    public String getBranchID() {
        return branchID;
    }

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchSortBy() {
        return branchSortBy;
    }

    public void setBranchSortBy(String branchSortBy) {
        this.branchSortBy = branchSortBy;
    }

    public String getBranchCreatedBy() {
        return branchCreatedBy;
    }

    public void setBranchCreatedBy(String branchCreatedBy) {
        this.branchCreatedBy = branchCreatedBy;
    }

    public String getBranchEdit() {
        return branchEdit;
    }

    public void setBranchEdit(String branchEdit) {
        this.branchEdit = branchEdit;
    }

    public String getBranchCreatedDate() {
        return branchCreatedDate;
    }

    public void setBranchCreatedDate(String branchCreatedDate) {
        this.branchCreatedDate = branchCreatedDate;
    }

    public String getBranchModifiedDate() {
        return branchModifiedDate;
    }

    public void setBranchModifiedDate(String branchModifiedDate) {
        this.branchModifiedDate = branchModifiedDate;
    }

    public String getBranchStatus() {
        return branchStatus;
    }

    public void setBranchStatus(String branchStatus) {
        this.branchStatus = branchStatus;
    }

    public String getBranchFlag() {
        return branchFlag;
    }

    public void setBranchFlag(String branchFlag) {
        this.branchFlag = branchFlag;
    }

}
