package com.example.primegen.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.primegen.MainActivity;
import com.example.primegen.R;
import com.example.primegen.base_properties.BaseResponse;
import com.example.primegen.databinding.ActivityProfileBinding;
import com.example.primegen.login.LoginActivity;
import com.example.primegen.signup.RegisterActivity;
import com.example.primegen.signup.User;
import com.example.primegen.viewmodel.PrimeViewModel;
import com.google.gson.Gson;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding mProfileBinding;
    private SharedPreferences mPrefs;
    private PrimeViewModel viewModel;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProfileBinding = ActivityProfileBinding.inflate(LayoutInflater.from(this));
        setContentView(mProfileBinding.getRoot());

        viewModel = new ViewModelProvider(this).get(PrimeViewModel.class);

        mPrefs = getSharedPreferences("user_values",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        user = gson.fromJson(json, User.class);

        mProfileBinding.userName.setText(user.getCustomerName());
        mProfileBinding.userEmail.setText(user.getCustomerEmail());
        mProfileBinding.userpassword.setText(user.getCustomerPassword());
        setUpdateUser();
    }

    public void setUpdateUser(){
        mProfileBinding.btnUpdate.setOnClickListener(v -> {

            String username = mProfileBinding.userName.getText().toString();
            String email = mProfileBinding.userEmail.getText().toString();
            String password = mProfileBinding.userpassword.getText().toString();

            user.setCustomerName(username);
            user.setCustomerEmail(email);
            user.setCustomerPassword(password);

            if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)
                    && !TextUtils.isEmpty(email)) {

//                viewModel.updateUser(user.getCustomerID(),username,email,password).observe(ProfileActivity.this, baseResponse -> {
//
//                    if (baseResponse.getMessage().equals("Invalid email format")){
//                        Toast.makeText(ProfileActivity.this,baseResponse.getMessage(),Toast.LENGTH_SHORT).show();
//                    }else if(baseResponse.getMessage().equals("User Profile Update Successfully...!")){
//
//                        SharedPreferences.Editor prefsEditor = mPrefs.edit();
//                        Gson gson = new Gson();
//                        String json = gson.toJson(user);
//                        prefsEditor.putString("MyObject", json);
//                        prefsEditor.commit();
//
//                       // startActivity(new Intent(ProfileActivity.this, MainActivity.class));
//                        Toast.makeText(ProfileActivity.this,baseResponse.getMessage(),Toast.LENGTH_SHORT).show();
//                        finish();
//
//
//                    }else if(baseResponse.getMessage().equals("Password must be less than 20 and more than 6 characters")){
//                        Toast.makeText(ProfileActivity.this,baseResponse.getMessage(),Toast.LENGTH_SHORT).show();
//
//                    }else {
//                        Toast.makeText(ProfileActivity.this,"Register Failed",Toast.LENGTH_SHORT).show();
//
//                    }
//
//                });
            }else {
                mProfileBinding.userName.setError("enter the value");
                mProfileBinding.userEmail.setError("enter the value");
                mProfileBinding.userpassword.setError("enter the value");
            }
        });
    }
}