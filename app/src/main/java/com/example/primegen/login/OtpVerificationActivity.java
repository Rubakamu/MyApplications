package com.example.primegen.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.primegen.databinding.ActivityOtpVerificationBinding;
import com.example.primegen.forgotpassword.ChangePasswordActivity;
import com.example.primegen.forgotpassword.ForgotPasswordActivity;
import com.example.primegen.viewmodel.PrimeViewModel;


public class OtpVerificationActivity extends AppCompatActivity {

    private ActivityOtpVerificationBinding mOtpBinding;
    private PrimeViewModel viewModel;
    private int intValue;

    boolean isRegister;
    boolean isForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOtpBinding = ActivityOtpVerificationBinding.inflate(LayoutInflater.from(this));
        setContentView(mOtpBinding.getRoot());

        viewModel = new ViewModelProvider(this).get(PrimeViewModel.class);
        otpVerification();

        Intent mIntent = getIntent();
        intValue = mIntent.getIntExtra("cid", 0);
        if (getIntent().getExtras().getBoolean("isRegister")) {
            isRegister = getIntent().getExtras().getBoolean("isRegister");
        }else {
            isForgotPassword = getIntent().getExtras().getBoolean("isForgotPassword");
        }

        mOtpBinding.back.setOnClickListener(v ->
                onBackPressed());

    }

    private void otpVerification() {

        mOtpBinding.btnVerify.setOnClickListener(v -> {

            String otp = String.valueOf(mOtpBinding.edtVerifyOtp.getText());

            viewModel.otpVerification(otp, String.valueOf(intValue)).observe(OtpVerificationActivity.this, loginResponse -> {
                if (loginResponse.getMessage().equals("Something went wrong. Please try again later...!")) {
                    Toast.makeText(OtpVerificationActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else if (loginResponse.getMessage().equals("verified successfully...!")) {
                    clear();

                    if(isRegister) {
                        startActivity(new Intent(OtpVerificationActivity.this, LoginActivity.class));
                        Toast.makeText(OtpVerificationActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }else if(isForgotPassword){

                        Intent intent = new Intent(OtpVerificationActivity.this, ChangePasswordActivity.class);
                        intent.putExtra("cid",intValue);
                        startActivity(intent);
                        //startActivity(new Intent(OtpVerificationActivity.this, ChangePasswordActivity.class));
                        Toast.makeText(OtpVerificationActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            });
        });
    }

    public void clear() {
        mOtpBinding.edtVerifyOtp.setText("");

    }
}