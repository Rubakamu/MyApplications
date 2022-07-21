package com.example.primegen.forgotpassword;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.primegen.base_properties.BaseResponse;
import com.example.primegen.databinding.ActivityForgotPasswordBinding;
import com.example.primegen.login.OtpVerificationActivity;
import com.example.primegen.viewmodel.PrimeViewModel;

public class ForgotPasswordActivity extends AppCompatActivity {

    private ActivityForgotPasswordBinding mForgotPasswordBinding;
    private PrimeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mForgotPasswordBinding = ActivityForgotPasswordBinding.inflate(LayoutInflater.from(this));
        setContentView(mForgotPasswordBinding.getRoot());
        viewModel = new ViewModelProvider(this).get(PrimeViewModel.class);

        mForgotPasswordBinding.btnChange.setOnClickListener(v -> {
            String phone = mForgotPasswordBinding.edtEmail.getText().toString();

            viewModel.forgotPassword(phone).observe(ForgotPasswordActivity.this, baseResponse -> {

                if (baseResponse.getMessage().equals("Something went wrong. Please try again later...!")) {
                    Toast.makeText(ForgotPasswordActivity.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else if (baseResponse.getMessage().equals("Success..!")) {
                    clear();
                    Intent intent = new Intent(ForgotPasswordActivity.this, OtpVerificationActivity.class);
                    intent.putExtra("cid", baseResponse.getCid());
                    intent.putExtra("isForgotPassword", true);
                    startActivity(intent);
                    Toast.makeText(ForgotPasswordActivity.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                } else if (baseResponse.getMessage().equals("Incorrect Mobile Number We Cannot find an account with that mobile number associated with your Primegen account.")) {
                    Toast.makeText(ForgotPasswordActivity.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ForgotPasswordActivity.this, "Failed", Toast.LENGTH_SHORT).show();

                }
            });
        });
        mForgotPasswordBinding.back.setOnClickListener(v -> onBackPressed());
    }

    public void clear() {
        mForgotPasswordBinding.edtEmail.setText("");

    }
}