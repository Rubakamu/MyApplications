package com.example.primegen.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.primegen.base_properties.BaseResponse;
import com.example.primegen.login.LoginResponse;
import com.example.primegen.repository.UserRepository;
import com.example.primegen.signup.UserResponse;
import com.example.primegen.test.TestResponse;

public class PrimeViewModel extends AndroidViewModel {
    private UserRepository userRepository;

    public PrimeViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }
    public LiveData<TestResponse> getAllTestData() {
        return userRepository.getTestLiveData();
    }
    public LiveData<BaseResponse> insertUser(String username, String customerName, String email, String password) {
        return userRepository.getCreateUserLiveData(username,customerName, email, password);
    }
    public LiveData<LoginResponse> loginUser(String email, String password) {
        return userRepository.getLoginLiveData(email, password);
    }


    public LiveData<BaseResponse> updateUser(String customerID, String customerTitle,String customerName, String customerLastname,
                                             String customerUsername, String customerPassword ,String customerEmail , String customerDOB,
                                             String customerAge ,String customerGender ,String customerBloodGroup){


        return userRepository.getUpdateUserLiveData(customerID,customerTitle,customerName,customerLastname,customerUsername,
                customerPassword,customerEmail,customerDOB,customerAge,customerGender,customerBloodGroup);


    }
    public LiveData<LoginResponse> otpVerification(String otp, String id) {
        return userRepository.getOtpLiveData(otp, id);
    }
    public LiveData<BaseResponse> forgotPassword(String phone) {
        return userRepository.getForgotPasswordLiveData(phone);
    }
    public LiveData<LoginResponse> resetPassword(String resetPassword, String id) {
        return userRepository.getResetPasswordLiveData(resetPassword, id);
    }
}
