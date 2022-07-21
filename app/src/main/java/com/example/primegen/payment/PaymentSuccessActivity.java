package com.example.primegen.payment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import com.example.primegen.DashboardActivity;
import com.example.primegen.databinding.ActivityPaymentSuccessBinding;
import com.example.primegen.login.LoginActivity;

public class PaymentSuccessActivity extends AppCompatActivity {
    private ActivityPaymentSuccessBinding mSuccessBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSuccessBinding = ActivityPaymentSuccessBinding.inflate(LayoutInflater.from(this));
        setContentView(mSuccessBinding.getRoot());


        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            finish();
        },2000);
    }
}
