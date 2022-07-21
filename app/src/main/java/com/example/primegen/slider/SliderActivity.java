package com.example.primegen.slider;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.primegen.DashboardActivity;
import com.example.primegen.R;
import com.example.primegen.databinding.ActivitySliderBinding;
import com.example.primegen.login.LoginActivity;
import com.example.primegen.login.PhoneLoginActivity;
import com.example.primegen.welcome.WelcomeActivity;


public class SliderActivity extends AppCompatActivity {
    ActivitySliderBinding mSliderBinding;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSliderBinding = ActivitySliderBinding.inflate(LayoutInflater.from(this));
        setContentView(mSliderBinding.getRoot());
        //setStatusBarColor();
        mSliderBinding.viewPager.setAdapter(createCardAdapter());

        mSliderBinding.ivGetStart.setOnClickListener(v -> {
            int current = getItem(+1);
            if (current < 3) {
                mSliderBinding.viewPager.setCurrentItem(current);
            } else {
                launchWelcomeScreen();

            }
        });

    }

    private void launchWelcomeScreen() {
        startActivity(new Intent(SliderActivity.this, LoginActivity.class));
    }
    private int getItem(int i) {

        return mSliderBinding.viewPager.getCurrentItem() + 1;
    }
    private ViewPagerAdapter createCardAdapter() {
        adapter = new ViewPagerAdapter(this);
        return adapter;
    }
    private void setStatusBarColor() {
        Window window = SliderActivity.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(SliderActivity.this, R.color.white_light));
    }

}