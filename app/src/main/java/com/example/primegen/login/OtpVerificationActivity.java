package com.example.primegen.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.primegen.DashboardActivity;
import com.example.primegen.databinding.ActivityOtpVerificationBinding;
import com.example.primegen.forgotpassword.ChangePasswordActivity;
import com.example.primegen.signup.User;
import com.example.primegen.viewmodel.PrimeViewModel;
import com.google.gson.Gson;


public class OtpVerificationActivity extends AppCompatActivity {

    private ActivityOtpVerificationBinding mOtpBinding;
    private PrimeViewModel viewModel;
    private SharedPreferences mPrefs;
    private int intValue;
    boolean isRegister;
    boolean isForgotPassword;
    String phone;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOtpBinding = ActivityOtpVerificationBinding.inflate(LayoutInflater.from(this));
        setContentView(mOtpBinding.getRoot());

        viewModel = new ViewModelProvider(this).get(PrimeViewModel.class);

        mPrefs = getSharedPreferences("user_values", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        com.example.primegen.signup.User obj = gson.fromJson(json, User.class);

        Intent mIntent = getIntent();
        intValue = mIntent.getIntExtra("cid", 0);
        if (getIntent().getExtras().getBoolean("isRegister")) {
            isRegister = getIntent().getExtras().getBoolean("isRegister");
            phone = getIntent().getExtras().getString("phone");
            password = getIntent().getExtras().getString("password");
        } else {
            isForgotPassword = getIntent().getExtras().getBoolean("isForgotPassword");
        }

        mOtpBinding.back.setOnClickListener(v ->
                onBackPressed());

        otpVerification();
    }

    private void otpVerification() {

        mOtpBinding.btnVerify.setOnClickListener(v -> {

            String otp = String.valueOf(mOtpBinding.edtVerifyOtp.getText());

            viewModel.otpVerification(otp, String.valueOf(intValue)).observe(OtpVerificationActivity.this, loginResponse -> {
                if (loginResponse.getMessage().equals("Something went wrong. Please try again later...!")) {
                    //Toast.makeText(OtpVerificationActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    mOtpBinding.responseText.setText(loginResponse.getMessage());
                } else if (loginResponse.getMessage().equals("verified successfully...!")) {
                    //clear();

                    if (isRegister) {
                        setSignUpLogin();
                        mOtpBinding.responseText.setText(loginResponse.getMessage());
                        finish();
                    } else if (isForgotPassword) {

                        Intent intent = new Intent(OtpVerificationActivity.this, ChangePasswordActivity.class);
                        intent.putExtra("cid", intValue);
                        startActivity(intent);
                        mOtpBinding.responseText.setText(loginResponse.getMessage());
                        finish();
                    }
                } else if (loginResponse.getMessage().equals("Incorrect OTP")) {
                    mOtpBinding.responseText.setText(loginResponse.getMessage());
                }
            });
        });
    }

    private void setSignUpLogin() {

        if (isRegister) {
            if (!TextUtils.isEmpty(password) && !TextUtils.isEmpty(phone)) {

                viewModel.loginUser(phone, password).observe(OtpVerificationActivity.this, loginResponse -> {

                    if (loginResponse.getMessage().equals("Login Success")) {

                        SharedPreferences.Editor prefsEditor = mPrefs.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(loginResponse.getUser());
                        prefsEditor.putString("MyObject", json);
                        prefsEditor.apply();
                        Intent intent = new Intent(OtpVerificationActivity.this, DashboardActivity.class);
                        intent.putExtra("isRegister", true);
                        startActivity(intent);
                        mOtpBinding.responseText.setText(loginResponse.getMessage());
                        finish();

                    } else if (loginResponse.getMessage().equals("Incorrect Mobile Number/Password We Cannot find an account with that mobile number/password")) {
                        mOtpBinding.responseText.setText(loginResponse.getMessage());
                    }

                });

            }
        }
    }

    public void clear() {
        mOtpBinding.edtVerifyOtp.setText("");

    }
}