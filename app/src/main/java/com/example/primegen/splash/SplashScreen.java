package com.example.primegen.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.primegen.DashboardActivity;
import com.example.primegen.MainActivity;
import com.example.primegen.R;
import com.example.primegen.login.LoginActivity;
import com.example.primegen.signup.RegisterActivity;
import com.example.primegen.signup.User;
import com.example.primegen.slider.LuncherManager;
import com.example.primegen.slider.SliderActivity;
import com.google.gson.Gson;

public class SplashScreen extends AppCompatActivity {
    LuncherManager luncherManager;
    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        setStatusBarColor();
        luncherManager = new LuncherManager(this);
        mPrefs = getSharedPreferences("user_values",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        User user = gson.fromJson(json, User.class);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if(user != null){
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                String json1 = gson.toJson(user);
                prefsEditor.putString("MyObject", json1);
                prefsEditor.apply();
                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                finish();
            }else {
                startActivity(new Intent(getApplicationContext(), SliderActivity.class));
                finish();

            }


        },2000);
    }

    private void setStatusBarColor() {
        Window window = SplashScreen.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(SplashScreen.this, R.color.primege_light));
    }
}