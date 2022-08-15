package com.example.primegen.api;

import com.example.primegen.base_properties.BaseResponse;
import com.example.primegen.base_properties.BranchResponse;
import com.example.primegen.login.LoginResponse;
import com.example.primegen.profile.family.FamilyResponse;
import com.example.primegen.profile.my_address.AddressBookResponse;
import com.example.primegen.profile.my_test.BookingHistoryResponse;
import com.example.primegen.profile.my_test.booking_details.BookingDetailsResponse;
import com.example.primegen.signup.User;
import com.example.primegen.test.TestResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApiService {

    @FormUrlEncoded
    @POST("sign_up.php")
    Call<User> insertUser(
            @Field("customerName") String customerName,
            @Field("customerUsername") String username,
            @Field("customerEmail") String email,
            @Field("customerPassword") String password
    );

    @FormUrlEncoded
    @POST("otp_verify.php")
    Call<LoginResponse> otpVerification(
            @Field("customerOTP") String otp,
            @Field("customerID") String id
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> loginUser(
            @Field("customerUsername") String email,
            @Field("customerPassword") String password
    );

    @FormUrlEncoded
    @POST("forgot_password.php")
    Call<LoginResponse> forgotPassword(
            @Field("customerPhone") String phone);

    @FormUrlEncoded
    @POST("reset_password.php")
    Call<LoginResponse> setResetPassword(
            @Field("customerPassword") String resetPassword,
            @Field("customerID") String id
    );


    @GET("testprofilelist.php")
    Call<TestResponse> getAllTestData();


    @FormUrlEncoded
    @POST("update_profile.php")
    Call<User> updateUser(
            @Field("customerID") String customerID,
            @Field("customerTitle") String customerTitle,
            @Field("customerName") String customerName,
            @Field("customerLastname") String customerLastname,
            @Field("customerUsername") String customerUsername,
            @Field("customerPassword") String customerPassword,
            @Field("customerEmail") String customerEmail,
            @Field("customerDOB") String customerDOB,
            @Field("customerAge") String customerAge,
            @Field("customerGender") String customerGender,
            @Field("customerBloodGroup") String customerBloodGroup
    );


    @FormUrlEncoded
    @POST("address_book.php")
    Call<AddressBookResponse> addressBook(@Field("customerID") String customerID);

    @FormUrlEncoded
    @POST("add_address.php")
    Call<AddressBookResponse> addAddress(
            @Field("customerID") String customerID,
            @Field("customerName") String customerName,
            @Field("customerUsername") String customerUsername,
            @Field("customerEmail") String customerEmail,
            @Field("abAddressline1") String abAddressline1,
            @Field("abAddressline2") String abAddressline2,
            @Field("abCity") String abCity,
            @Field("abState") String abState,
            @Field("abLandMark") String abLandMark,
            @Field("abPincode") String abPincode,
            @Field("abAddressType") String abAddressType,
            @Field("abLabel") String abLabel,
            @Field("abLocation") String abLocation,
            @Field("abCurrent") String abCurrent,
            @Field("abGender") String abGender,
            @Field("abDOB") String abDOB,
            @Field("abRelationship") String abRelationship);

    @FormUrlEncoded
    @POST("family_member_list.php")
    Call<FamilyResponse> familyList(
            @Field("customerID") String customerID);

    @FormUrlEncoded
    @POST("add_family_member.php")
    Call<FamilyResponse> addFamilyMember(
            @Field("customerID") String customerID,
            @Field("relativeName") String relativeName,
            @Field("relativeAltMobile") String relativeAltMobile,
            @Field("relativeDOB") String relativeDOB,
            @Field("relativeGender") String relativeGender,
            @Field("customerRelationship") String customerRelationship);

    @FormUrlEncoded
    @POST("booking_history.php")
    Call<BookingHistoryResponse> bookingHistory(
            @Field("customerID") String customerID);

    @FormUrlEncoded
    @POST("booking_details.php")
    Call<BookingDetailsResponse> bookingDetails(
            @Field("customerID") String customerID,
            @Field("ouid") String bookingUniqueID);


    @FormUrlEncoded
    @POST("checkout.php")
    Call<BaseResponse> checkout(
            @Field("customerID") String customerID,
            @Field("bookingRequestedDate") String bookingRequestedDate,
            @Field("bookingNotes") String bookingNotes,
            @Field("abID") String abID,
            @Field("bookingPaymentMode") String bookingPaymentMode,
            @Field("SessbookingFullAddress") String SessbookingFullAddress,
            @Field("bookingTests") String bookingTests,
            @Field("SessbookingTotalPackages") String SessbookingTotalPackages,
            @Field("SessbookingTotalQty") String SessbookingTotalQty,
            @Field("SessbookingSubtotal") String SessbookingSubtotal,
            @Field("SessbookingTotalAmount") String SessbookingTotalAmount,
            @Field("bookingCouponID") String bookingCouponID,
            @Field("bookingCouponAmount") String bookingCouponAmount,
            @Field("relativeID") String relativeID,
            @Field("branchLocation") String branchLocation,
            //booking details
            @Field("bookingtestprofileID") ArrayList<String> bookingtestprofileID,
            @Field("bookingTestPackageQty") ArrayList<String> bookingTestPackageQty,
            @Field("bookingTestPackageAmount") ArrayList<String> bookingTestPackageAmount,
            @Field("bookingdetailSubtotal") ArrayList<String> bookingdetailSubtotal,
            @Field("bookingTestorProfile") ArrayList<String> bookingTestorProfile
    );
    //String bookingtestprofileID,String bookingTestPackageQty,String bookingTestPackageAmount,String bookingdetailSubtotal,String bookingTestorProfile,String SessbookingFullAddress, String bookingTests,String SessbookingTotalPackages,String SessbookingTotalQty,String SessbookingSubtotal,String SessbookingTotalAmount,String bookingCouponID,String bookingCouponAmount,String relativeID, String branchLocation

    @GET("branch.php")
    Call<BranchResponse> getAllBranchData();

    @POST("delete_family.php")
    Call<BaseResponse> deleteFamilyMember(

    );



}
