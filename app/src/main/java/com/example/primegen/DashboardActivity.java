package com.example.primegen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.primegen.databinding.ActivityDashboardBinding;
import com.example.primegen.signup.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

public class DashboardActivity extends AppCompatActivity {

    private ActivityDashboardBinding binding;
    private SharedPreferences mPrefs;
    private User user;
    private boolean isRegister;
    private boolean isLogin;
    private boolean isSplash;
    private boolean isSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mPrefs = getSharedPreferences("user_values", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        user = gson.fromJson(json, User.class);

        setSupportActionBar(binding.toolbar);
        binding.toolbar.setTitleTextColor(getResources().getColor(R.color.primary_color));


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            boolean isCartLogin = bundle.getBoolean("cart_login");

            if (isCartLogin) {

                setSharedPreferenceValue();
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_add_to_cart);
            }

        }

        Intent mIntent = getIntent();
        if (mIntent != null) {
            if (mIntent.getExtras().getBoolean("isRegister")) {
                isRegister = mIntent.getExtras().getBoolean("isRegister");
            } else if (mIntent.getExtras().getBoolean("isLogin")) {
                isLogin = mIntent.getExtras().getBoolean("isLogin");
            }else if (mIntent.getExtras().getBoolean("isSuccess")) {
                isSuccess = mIntent.getExtras().getBoolean("isSuccess");
            }else {
                isSplash = mIntent.getExtras().getBoolean("isSplash");
            }
        }

//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_dashboard);
//        NavigationUI.setupWithNavController(bottomNavigationView, navController);


        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_userprofile)
                .build();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_activity_dashboard);

        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_dashboard);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(DashboardActivity.this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        binding.toolbar.setNavigationOnClickListener(view -> {
            navController.navigateUp();
        });

        binding.navView.setOnItemSelectedListener(item -> {
            View view = binding.getRoot().findViewById(R.id.nav_host_fragment_activity_dashboard);

            if (item.getItemId() == R.id.navigation_home) {
                Navigation.findNavController(view).navigate(R.id.navigation_home);
            } else if (item.getItemId() == R.id.navigation_dashboard) {
                setSharedPreferenceValue();
                Navigation.findNavController(view).navigate(R.id.navigation_dashboard);
            } else {
                setSharedPreferenceValue();
                Navigation.findNavController(view).navigate(R.id.navigation_userprofile);
            }

            return false;
        });

//        if (isRegister) {
//            setSharedPreferenceValue();
//            Navigation.findNavController(binding.getRoot()).navigate(R.id.navigation_my_account);
//        }

    }

    public void setSharedPreferenceValue() {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson1 = new Gson();
        String json1 = gson1.toJson(user);
        prefsEditor.putString("MyObject", json1);
        prefsEditor.commit();
    }

}