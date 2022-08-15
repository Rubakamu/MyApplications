package com.example.primegen.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.primegen.DashboardActivity;
import com.example.primegen.databinding.ActivityLoginBinding;
import com.example.primegen.forgotpassword.ForgotPasswordActivity;
import com.example.primegen.signup.RegisterActivity;
import com.example.primegen.signup.User;
import com.example.primegen.viewmodel.PrimeViewModel;
import com.google.gson.Gson;


public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding mBinding;
    private PrimeViewModel viewModel;
    private SharedPreferences mPrefs;
    private boolean isCartLogin;
    private int intValue;
    boolean isRegister;
    boolean isForgotPassword;
    String phone;
    String password;
    private boolean isSlider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLoginBinding.inflate(LayoutInflater.from(this));
        setContentView(mBinding.getRoot());

        viewModel = new ViewModelProvider(this).get(PrimeViewModel.class);
        mPrefs = getSharedPreferences("user_values", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        User obj = gson.fromJson(json, User.class);

//        Bundle bundle = getIntent().getExtras();
//        isCartLogin = bundle.getBoolean("cart_login");

        loginUser();
        mBinding.loginLayout.signUp.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            finishAffinity();
        });
        mBinding.loginLayout.tvForgotPassword.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class)));
        mBinding.loginLayout.back.setOnClickListener(v -> onBackPressed());

    }

    public void loginUser() {

            mBinding.loginLayout.btnSignIn.setOnClickListener(view -> {
                String email = mBinding.loginLayout.edtEmail.getText().toString();
                String password = mBinding.loginLayout.editTextPassword.getText().toString();

                if (!TextUtils.isEmpty(password) && !TextUtils.isEmpty(email)) {

                    viewModel.loginUser(email, password).observe(LoginActivity.this, loginResponse -> {

                        if (loginResponse.getMessage().equals("Login Success")) {

                            SharedPreferences.Editor prefsEditor = mPrefs.edit();
                            Gson gson = new Gson();
                            String json = gson.toJson(loginResponse.getUser());
                            prefsEditor.putString("MyObject", json);
                            prefsEditor.apply();
                            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                            intent.putExtra("isLogin", true);
                            startActivity(intent);
                            mBinding.loginLayout.responseText.setText(loginResponse.getMessage());
                            //  Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        } else if (loginResponse.getMessage().equals("Incorrect Mobile Number/Password We Cannot find an account with that mobile number/password")) {
                            //  Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            mBinding.loginLayout.responseText.setText(loginResponse.getMessage());
                        }

                    });


                } else if (TextUtils.isEmpty(password) && TextUtils.isEmpty(email)) {
                    mBinding.loginLayout.edtEmail.setError("Please enter the email");
                    mBinding.loginLayout.editTextPassword.setError("enter the value");
                } else if (TextUtils.isEmpty(password) && !TextUtils.isEmpty(email)) {
                    mBinding.loginLayout.editTextPassword.setError("enter the value");
                } else if (TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                    mBinding.loginLayout.edtEmail.setError("Please enter the email");
                }
            });
        }


}