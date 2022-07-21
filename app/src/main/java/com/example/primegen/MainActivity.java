package com.example.primegen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.primegen.databinding.ActivityMainBinding;
import com.example.primegen.login.LoginActivity;
import com.example.primegen.profile.ProfileActivity;
import com.example.primegen.signup.User;
import com.example.primegen.viewmodel.PrimeViewModel;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    private SharedPreferences mPrefs;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(mBinding.getRoot());


        mBinding.userProfile.setOnClickListener(v -> {
            SharedPreferences.Editor prefsEditor = mPrefs.edit();
            Gson gson1 = new Gson();
            String json1 = gson1.toJson(user);
            prefsEditor.putString("MyObject", json1);
            prefsEditor.commit();
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
        });
        mBinding.logout.setOnClickListener(v -> {
            SharedPreferences.Editor prefsEditor = mPrefs.edit();
            prefsEditor.clear();
            prefsEditor.apply();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        mPrefs = getSharedPreferences("user_values",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        user= gson.fromJson(json, User.class);

        mBinding.userName.setText(user.getCustomerName());

    }
}
