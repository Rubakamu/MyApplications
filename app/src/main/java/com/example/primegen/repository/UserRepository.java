package com.example.primegen.repository;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.primegen.base_properties.BaseResponse;
import com.example.primegen.api.RestApiService;
import com.example.primegen.api.RetrofitInstance;
import com.example.primegen.login.LoginResponse;
import com.example.primegen.signup.User;
import com.example.primegen.test.Test;
import com.example.primegen.test.TestResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private ArrayList<User> users = new ArrayList<>();
    private MutableLiveData<TestResponse> testLiveData =new MutableLiveData<>();
    private MutableLiveData<BaseResponse> createUserLiveData =new MutableLiveData<>();
    private MutableLiveData<BaseResponse> updateUserLiveData =new MutableLiveData<>();
    private MutableLiveData<LoginResponse> loginLiveData =new MutableLiveData<>();
    private MutableLiveData<LoginResponse> otpLiveData =new MutableLiveData<>();
    private MutableLiveData<BaseResponse> forgotPasswordLiveData =new MutableLiveData<>();
    private MutableLiveData<LoginResponse> resetPasswordLiveData =new MutableLiveData<>();

    private Application application;
    public UserRepository(Application application){

        this.application = application;
    }

    public MutableLiveData<TestResponse> getTestLiveData(){
        RestApiService test = RetrofitInstance.getApiService();
        Call<TestResponse> call = test.getAllTestData();

        call.enqueue(new Callback<TestResponse>() {
            @Override
            public void onResponse(Call<TestResponse> call, Response<TestResponse> response) {
                testLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<TestResponse> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

        return testLiveData;
    }

    public MutableLiveData<BaseResponse> getCreateUserLiveData(String username,String customerName, String email, String password){
        RestApiService user = RetrofitInstance.getApiService();
        Call<User> call = user.inserUser(username,customerName,email,password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                createUserLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

        return createUserLiveData;
    }


    public MutableLiveData<LoginResponse> getLoginLiveData(String email, String password){
        RestApiService user = RetrofitInstance.getApiService();
        Call<LoginResponse> call = user.loginUser(email,password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                loginLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
        return loginLiveData;
    }

    public MutableLiveData<BaseResponse> getUpdateUserLiveData(String customerID, String customerTitle,String customerName, String customerLastname,
                                                               String customerUsername, String customerPassword ,String customerEmail , String customerDOB,
                                                               String customerAge ,String customerGender ,String customerBloodGroup){
        RestApiService updateUser = RetrofitInstance.getApiService();
        Call<User> call = updateUser.updateUser(customerID,customerTitle,customerName,customerLastname,customerUsername,
                customerPassword,customerEmail,customerDOB,customerAge,customerGender,customerBloodGroup);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                updateUserLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        return updateUserLiveData;
    }


    public MutableLiveData<LoginResponse> getOtpLiveData(String otp,String id){
        RestApiService otpVerification = RetrofitInstance.getApiService();
        Call<LoginResponse> call = otpVerification.otpVerification(otp,id);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                otpLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

        return otpLiveData;
    }

    public MutableLiveData<BaseResponse> getForgotPasswordLiveData(String phone){
        RestApiService forgotPassword = RetrofitInstance.getApiService();
        Call<LoginResponse> call = forgotPassword.forgotPassword(phone);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                forgotPasswordLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
        return forgotPasswordLiveData;
    }

    public MutableLiveData<LoginResponse> getResetPasswordLiveData(String resetPassword, String id){
        RestApiService changePassword = RetrofitInstance.getApiService();
        Call<LoginResponse> call = changePassword.setResetPassword(resetPassword,id);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                resetPasswordLiveData.postValue(response.body());
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        return resetPasswordLiveData;
    }

}
