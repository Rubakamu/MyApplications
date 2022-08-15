package com.example.primegen.signup;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.primegen.databinding.ActivityRegisterBinding;
import com.example.primegen.login.LoginActivity;
import com.example.primegen.login.OtpVerificationActivity;
import com.example.primegen.viewmodel.PrimeViewModel;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding mRegisterBinding;
    private PrimeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRegisterBinding = ActivityRegisterBinding.inflate(LayoutInflater.from(this));
        setContentView(mRegisterBinding.getRoot());

        viewModel = new ViewModelProvider(this).get(PrimeViewModel.class);

        createUserData();

        mRegisterBinding.register.signIn.setOnClickListener(v ->{
                //startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            intent.putExtra("isRegister", false);
            startActivity(intent);

    });
        mRegisterBinding.register.back.setOnClickListener(v -> onBackPressed());
    }

    public void createUserData() {

        mRegisterBinding.register.btnSignIn.setOnClickListener(v -> {

            String username = mRegisterBinding.register.edtName.getText().toString();
            String phone = mRegisterBinding.register.edtMobile.getText().toString();
            String email = mRegisterBinding.register.edtEmail.getText().toString();
            String password = mRegisterBinding.register.edtPassword.getText().toString();

            if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)
                    && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(phone)) {

                viewModel.insertUser(username, phone, email, password).observe(RegisterActivity.this, baseResponse -> {

                    if (baseResponse.getMessage().equals("Something went wrong. Please try again later...!")) {
                        mRegisterBinding.register.responseText.setText(baseResponse.getMessage());
                        //Toast.makeText(RegisterActivity.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    } else if (baseResponse.getMessage().equals("Success..!")) {

                        Intent intent = new Intent(RegisterActivity.this, OtpVerificationActivity.class);
                        intent.putExtra("cid", baseResponse.getCid());
                        intent.putExtra("isRegister", true);
                        intent.putExtra("phone", phone);
                        intent.putExtra("password", password);
                        startActivity(intent);
                        clear();
                        // Toast.makeText(RegisterActivity.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        mRegisterBinding.register.responseText.setText(baseResponse.getMessage());

                        finish();
                    } else if (baseResponse.getMessage().equals("Customer with that mobile number already...!")) {
                        //Toast.makeText(RegisterActivity.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        mRegisterBinding.register.responseText.setText(baseResponse.getMessage());

                    } else if (baseResponse.getMessage().equals("Email ID already registered...!")) {
                        //Toast.makeText(RegisterActivity.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        mRegisterBinding.register.responseText.setText(baseResponse.getMessage());

                    } else if (baseResponse.getMessage().equals("Password must be less than 20 and more than 6 characters")) {
                        //Toast.makeText(RegisterActivity.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        mRegisterBinding.register.responseText.setText(baseResponse.getMessage());

                    }

                });

            } else if (TextUtils.isEmpty(username) && TextUtils.isEmpty(email) && TextUtils.isEmpty(phone) && TextUtils.isEmpty(password)) {
                mRegisterBinding.register.edtName.setError("Name is Require");
                mRegisterBinding.register.edtEmail.setError("Email is Require");
                mRegisterBinding.register.edtMobile.setError("phone is Require");
                mRegisterBinding.register.edtPassword.setError("Password is Require");
            } else if (TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password)) {
                mRegisterBinding.register.edtName.setError("Name is Require");
            } else if (TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password)) {
                mRegisterBinding.register.edtName.setError("Name is Require");
            } else if (!TextUtils.isEmpty(username) && TextUtils.isEmpty(email) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password)) {
                mRegisterBinding.register.edtEmail.setError("Email is Require");
            } else if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password)) {
                mRegisterBinding.register.edtMobile.setError("phone is Require");
            } else if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(phone) && TextUtils.isEmpty(password)) {
                mRegisterBinding.register.edtPassword.setError("Password is Require");
            } else if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && TextUtils.isEmpty(phone) && TextUtils.isEmpty(password)) {
                mRegisterBinding.register.edtPassword.setError("Password is Require");
                mRegisterBinding.register.edtMobile.setError("phone is Require");
            } else if (!TextUtils.isEmpty(username) && TextUtils.isEmpty(email) && !TextUtils.isEmpty(phone) && TextUtils.isEmpty(password)) {
                mRegisterBinding.register.edtPassword.setError("Password is Require");
                mRegisterBinding.register.edtMobile.setError("Email is Require");
            } else if (TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(phone) && TextUtils.isEmpty(password)) {
                mRegisterBinding.register.edtPassword.setError("Password is Require");
                mRegisterBinding.register.edtName.setError("Name is Require");
            } else if (TextUtils.isEmpty(username) && TextUtils.isEmpty(email) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password)) {
                mRegisterBinding.register.edtEmail.setError("Email is Require");
                mRegisterBinding.register.edtName.setError("Name is Require");
            } else if (TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password)) {
                mRegisterBinding.register.edtMobile.setError("Phone is Require");
                mRegisterBinding.register.edtName.setError("Name is Require");
            } else if (!TextUtils.isEmpty(username) && TextUtils.isEmpty(email) && TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password)) {
                mRegisterBinding.register.edtMobile.setError("Phone is Require");
                mRegisterBinding.register.edtEmail.setError("Email is Require");
            }

        });
    }

    public void clear() {
        mRegisterBinding.register.edtName.setText("");
        mRegisterBinding.register.edtEmail.setText("");
        mRegisterBinding.register.edtPassword.setText("");
    }


}