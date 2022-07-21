package com.example.primegen.api;

import com.example.primegen.base_properties.BaseResponse;
import com.example.primegen.login.LoginResponse;
import com.example.primegen.signup.User;
import com.example.primegen.signup.UserResponse;
import com.example.primegen.test.TestResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApiService {

    @FormUrlEncoded
    @POST("sign_up.php")
    Call<User> inserUser(
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
    Call<TestResponse>  getAllTestData();


    @FormUrlEncoded
    @POST("update_profile.php")
    Call<User> updateUser(
            @Field("customerID") String customerID,
            @Field("customerTitle") String customerTitle ,
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
    @POST("getuserdata.php")
    Call<UserResponse> remove(@Field("userID") String userID);
}
