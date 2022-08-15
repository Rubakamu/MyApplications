package com.example.primegen.payment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.primegen.DashboardActivity;
import com.example.primegen.databinding.ActivityPaymentSuccessBinding;

public class PaymentSuccessActivity extends AppCompatActivity {
    private ActivityPaymentSuccessBinding mSuccessBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSuccessBinding = ActivityPaymentSuccessBinding.inflate(LayoutInflater.from(this));
        setContentView(mSuccessBinding.getRoot());

        mSuccessBinding.btnDone.setOnClickListener(v -> {
            Intent intent = new Intent(PaymentSuccessActivity.this, DashboardActivity.class);
            intent.putExtra("isSuccess",true);
            startActivity(intent);
        });

//        new Handler(Looper.getMainLooper()).postDelayed(() -> {
//
//            startActivity(new Intent(PaymentSuccessActivity.this,DashboardActivity.class));
//
//            finish();
//        },2000);
    }
}
