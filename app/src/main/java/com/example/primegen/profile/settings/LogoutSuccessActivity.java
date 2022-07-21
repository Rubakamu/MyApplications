package com.example.primegen.profile.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.primegen.DashboardActivity;
import com.example.primegen.R;
import com.example.primegen.databinding.ActivityLogoutSuccessBinding;
import com.example.primegen.login.LoginActivity;
import com.example.primegen.slider.SliderActivity;
import com.google.gson.Gson;

public class LogoutSuccessActivity extends AppCompatActivity {
    private SharedPreferences mPrefs;
    private com.example.primegen.signup.User user;

    ActivityLogoutSuccessBinding logoutSuccessBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        logoutSuccessBinding = ActivityLogoutSuccessBinding.inflate(LayoutInflater.from(this));
        setContentView(R.layout.activity_logout_success);

        mPrefs = getSharedPreferences("user_values", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        user = gson.fromJson(json, com.example.primegen.signup.User.class);

    }

    public void setCancel(View view) {
        finish();
    }

    public void setLogout(View view) {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.clear();
        prefsEditor.apply();
        startActivity(new Intent(LogoutSuccessActivity.this, SliderActivity.class));
        finish();
    }
}