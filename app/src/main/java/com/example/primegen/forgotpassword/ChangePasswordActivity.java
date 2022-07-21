package com.example.primegen.forgotpassword;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.primegen.databinding.ActivityChangePasswordBinding;
import com.example.primegen.login.LoginActivity;
import com.example.primegen.login.LoginResponse;
import com.example.primegen.login.OtpVerificationActivity;
import com.example.primegen.viewmodel.PrimeViewModel;


public class ChangePasswordActivity extends AppCompatActivity {

    private ActivityChangePasswordBinding mChangePasswordBinding;
    private PrimeViewModel viewModel;
    private int cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChangePasswordBinding = ActivityChangePasswordBinding.inflate(LayoutInflater.from(this));
        setContentView(mChangePasswordBinding.getRoot());
        viewModel = new ViewModelProvider(this).get(PrimeViewModel.class);

        Intent mIntent = getIntent();
        cid = mIntent.getIntExtra("cid", 0);

        setResetPassword();
        mChangePasswordBinding.back.setOnClickListener(v -> onBackPressed());
    }

    private void setResetPassword() {
        mChangePasswordBinding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String password = mChangePasswordBinding.edtNewPassword.getText().toString();
                String confirmPassword = mChangePasswordBinding.edtConformPassword.getText().toString();

                if (password.equals(confirmPassword)) {

                    viewModel.resetPassword(password, String.valueOf(cid)).observe(ChangePasswordActivity.this, new Observer<LoginResponse>() {
                        @Override
                        public void onChanged(LoginResponse loginResponse) {
                            if (loginResponse.getMessage().equals("Something went wrong. Please try again later...!")) {
                                Toast.makeText(ChangePasswordActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            } else if (loginResponse.getMessage().equals("Password reset successfully...!")) {
                                clear();
                                startActivity(new Intent(ChangePasswordActivity.this, LoginActivity.class));
                                Toast.makeText(ChangePasswordActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                } else {
                    mChangePasswordBinding.edtConformPassword.setError("Password Mismatch");
                }
            }
        });

    }
    public void clear() {
        mChangePasswordBinding.edtNewPassword.setText("");
        mChangePasswordBinding.edtConformPassword.setText("");

    }
}
