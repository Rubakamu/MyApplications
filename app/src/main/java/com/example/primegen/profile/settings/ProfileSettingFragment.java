package com.example.primegen.profile.settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.primegen.R;
import com.example.primegen.cart.CartSingleTon;
import com.example.primegen.databinding.FragmentProfileSettingBinding;
import com.example.primegen.login.LoginActivity;
import com.google.gson.Gson;

public class ProfileSettingFragment extends Fragment {

    private FragmentProfileSettingBinding mSettingBinding;
    private SharedPreferences mPrefs;
    private com.example.primegen.signup.User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mSettingBinding = FragmentProfileSettingBinding.inflate(inflater);

        mPrefs = requireActivity().getSharedPreferences("user_values", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");

        user = gson.fromJson(json, com.example.primegen.signup.User.class);
        if (CartSingleTon.getInstance(requireActivity()).readItemCount() != 0) {
            mSettingBinding.tvCount.setText(String.valueOf(CartSingleTon.getInstance(requireActivity()).readItemCount()));
        }


        initView();

        setBackPress();
        return mSettingBinding.getRoot();
    }

    private void initView() {

        if(user !=null) {
            mSettingBinding.userName.setText(user.getCustomerName());
            mSettingBinding.userEmail.setText(user.getCustomerEmail());

        }
        mSettingBinding.privacyCard.setOnClickListener(v -> {
            Navigation.findNavController(mSettingBinding.getRoot()).navigate(R.id.action_privacy_policy);
    });
//        mSettingBinding.logoutCard.setOnClickListener(v -> {
//            SharedPreferences.Editor prefsEditor = mPrefs.edit();
//            Gson gson1 = new Gson();
//            String json1 = gson1.toJson(user);
//            prefsEditor.putString("MyObject", json1);
//            prefsEditor.commit();
//            startActivity(new Intent(requireActivity(), LogoutSuccessActivity.class));
//        });

        mSettingBinding.cvCart.setOnClickListener(v ->
                 Navigation.findNavController(mSettingBinding.getRoot()).navigate(R.id.action_add_to_cart));
    }

    private void setBackPress() {
        mSettingBinding.back.setOnClickListener(v -> {
            getActivity().onBackPressed();

        });
    }
}