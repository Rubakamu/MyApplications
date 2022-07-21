package com.example.primegen;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.primegen.databinding.ActivityDashboardBinding;
import com.example.primegen.signup.User;
import com.google.gson.Gson;

public class DashboardActivity extends AppCompatActivity {

    private ActivityDashboardBinding binding;
    private SharedPreferences mPrefs;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mPrefs = getSharedPreferences("user_values", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        user = gson.fromJson(json, User.class);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            boolean isCartLogin = bundle.getBoolean("cart_login");

            if (isCartLogin) {

                setSharedPreferenceValue();
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_add_to_cart);
            }

        }

        setSupportActionBar(binding.toolbar);
        binding.toolbar.setTitleTextColor(getResources().getColor(R.color.primary_color));

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);

        binding.toolbar.setNavigationIcon(null);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_userprofile)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_dashboard);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        NavigationUI.setupWithNavController(binding.navView, navController);

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

    }
    public void setSharedPreferenceValue(){
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson1 = new Gson();
        String json1 = gson1.toJson(user);
        prefsEditor.putString("MyObject", json1);
        prefsEditor.commit();
    }

}