package com.example.primegen.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("userID")
    @Expose
    private String userID;

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("userFullName")
    @Expose
    private String userFullName;
    @SerializedName("userPhone")
    @Expose
    private String userPhone;
    @SerializedName("userEmail")
    @Expose
    private String userEmail;
    @SerializedName("userAddress")
    @Expose
    private String userAddress;
    @SerializedName("userAadhar")
    @Expose
    private String userAadhar;

    /**
     * No args constructor for use in serialization
     *
     */
    public User() {
    }

    /**
     *
     * @param userAddress
     * @param password
     * @param userPhone
     * @param userFullName
     * @param userEmail
     * @param userAadhar
     * @param userID
     * @param username
     */
    public User(String userID, String username, String password, String userFullName, String userPhone, String userEmail, String userAddress, String userAadhar) {
        super();
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.userFullName = userFullName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.userAadhar = userAadhar;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserAadhar() {
        return userAadhar;
    }

    public void setUserAadhar(String userAadhar) {
        this.userAadhar = userAadhar;
    }



//
//    @SerializedName("userID")
//    @Expose
//    private Integer userID;
//    @SerializedName("userName")
//    @Expose
//    private String userName;
//    @SerializedName("userRole")
//    @Expose
//    private String userRole;
//    @SerializedName("userPassword")
//    @Expose
//    private String userPassword;
//    @SerializedName("userEmail")
//    @Expose
//    private String userEmail;
//    @SerializedName("userPhone")
//    @Expose
//    private String userPhone;
//    @SerializedName("userWarehouseID")
//    @Expose
//    private String userWarehouseID;
//    @SerializedName("userActiveStatus")
//    @Expose
//    private String userActiveStatus;
//    @SerializedName("userBlackSatus")
//    @Expose
//    private String userBlackSatus;
//    @SerializedName("userAddedBy")
//    @Expose
//    private String userAddedBy;
//    @SerializedName("userCreatedDate")
//    @Expose
//    private String userCreatedDate;
//    @SerializedName("userModifiedDate")
//    @Expose
//    private String userModifiedDate;
//    @SerializedName("userStatus")
//    @Expose
//    private Object userStatus;
//    @SerializedName("userFlag")
//    @Expose
//    private String userFlag;
//
//    public User(Integer userID, String userName, String userRole, String userPassword, String userEmail, String userPhone, String userWarehouseID, String userActiveStatus, String userBlackSatus, String userAddedBy, String userCreatedDate, String userModifiedDate, Object userStatus, String userFlag) {
//        super();
//        this.userID = userID;
//        this.userName = userName;
//        this.userRole = userRole;
//        this.userPassword = userPassword;
//        this.userEmail = userEmail;
//        this.userPhone = userPhone;
//        this.userWarehouseID = userWarehouseID;
//        this.userActiveStatus = userActiveStatus;
//        this.userBlackSatus = userBlackSatus;
//        this.userAddedBy = userAddedBy;
//        this.userCreatedDate = userCreatedDate;
//        this.userModifiedDate = userModifiedDate;
//        this.userStatus = userStatus;
//        this.userFlag = userFlag;
//    }
//
//    public Integer getUserID() {
//        return userID;
//    }
//
//    public void setUserID(Integer userID) {
//        this.userID = userID;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getUserRole() {
//        return userRole;
//    }
//
//    public void setUserRole(String userRole) {
//        this.userRole = userRole;
//    }
//
//    public String getUserPassword() {
//        return userPassword;
//    }
//
//    public void setUserPassword(String userPassword) {
//        this.userPassword = userPassword;
//    }
//
//    public String getUserEmail() {
//        return userEmail;
//    }
//
//    public void setUserEmail(String userEmail) {
//        this.userEmail = userEmail;
//    }
//
//    public String getUserPhone() {
//        return userPhone;
//    }
//
//    public void setUserPhone(String userPhone) {
//        this.userPhone = userPhone;
//    }
//
//    public String getUserWarehouseID() {
//        return userWarehouseID;
//    }
//
//    public void setUserWarehouseID(String userWarehouseID) {
//        this.userWarehouseID = userWarehouseID;
//    }
//
//    public String getUserActiveStatus() {
//        return userActiveStatus;
//    }
//
//    public void setUserActiveStatus(String userActiveStatus) {
//        this.userActiveStatus = userActiveStatus;
//    }
//
//    public String getUserBlackSatus() {
//        return userBlackSatus;
//    }
//
//    public void setUserBlackSatus(String userBlackSatus) {
//        this.userBlackSatus = userBlackSatus;
//    }
//
//    public String getUserAddedBy() {
//        return userAddedBy;
//    }
//
//    public void setUserAddedBy(String userAddedBy) {
//        this.userAddedBy = userAddedBy;
//    }
//
//    public String getUserCreatedDate() {
//        return userCreatedDate;
//    }
//
//    public void setUserCreatedDate(String userCreatedDate) {
//        this.userCreatedDate = userCreatedDate;
//    }
//
//    public String getUserModifiedDate() {
//        return userModifiedDate;
//    }
//
//    public void setUserModifiedDate(String userModifiedDate) {
//        this.userModifiedDate = userModifiedDate;
//    }
//
//    public Object getUserStatus() {
//        return userStatus;
//    }
//
//    public void setUserStatus(Object userStatus) {
//        this.userStatus = userStatus;
//    }
//
//    public String getUserFlag() {
//        return userFlag;
//    }
//
//    public void setUserFlag(String userFlag) {
//        this.userFlag = userFlag;
//    }
}
