package com.example.primegen.welcome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

import com.example.primegen.R;
import com.example.primegen.databinding.ActivityEntryBinding;
import com.example.primegen.login.LoginActivity;
import com.example.primegen.login.PhoneLoginActivity;
import com.example.primegen.signup.RegisterActivity;

public class WelcomeActivity extends AppCompatActivity {

    private ActivityEntryBinding mEntryBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEntryBinding = ActivityEntryBinding.inflate(LayoutInflater.from(this));
        setContentView(mEntryBinding.getRoot());
        setStatusBarColor();

        mEntryBinding.btnCreateAccount.setOnClickListener(v ->
                startActivity(new Intent(WelcomeActivity.this, PhoneLoginActivity.class)));

        mEntryBinding.btnSignIn.setOnClickListener(v ->
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class)));
    }
    private void setStatusBarColor() {
        Window window = WelcomeActivity.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(WelcomeActivity.this, R.color.purple_500));
    }
}