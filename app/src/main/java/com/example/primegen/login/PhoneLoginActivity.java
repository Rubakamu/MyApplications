package com.example.primegen.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.primegen.DashboardActivity;
import com.example.primegen.R;
import com.example.primegen.databinding.ActivityPhoneLoginBinding;
import com.rilixtech.widget.countrycodepicker.Country;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

public class PhoneLoginActivity extends AppCompatActivity {

    private ActivityPhoneLoginBinding mPhoneLoginBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPhoneLoginBinding = ActivityPhoneLoginBinding.inflate(LayoutInflater.from(this));
        setContentView(mPhoneLoginBinding.getRoot());

        mPhoneLoginBinding.countryCode.setOnCountryChangeListener(selectedCountry ->
                Toast.makeText(PhoneLoginActivity.this, "Updated " + selectedCountry.getName(), Toast.LENGTH_SHORT).show());
        mPhoneLoginBinding.btnSignIn.setOnClickListener(v ->
                startActivity(new Intent(PhoneLoginActivity.this, DashboardActivity.class)));
        mPhoneLoginBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}