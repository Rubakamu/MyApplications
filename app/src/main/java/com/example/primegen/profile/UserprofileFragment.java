package com.example.primegen.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.primegen.R;
import com.example.primegen.databinding.FragmentProfileLoginBinding;
import com.example.primegen.databinding.FragmentUserprofileBinding;
import com.example.primegen.login.LoginActivity;
import com.google.gson.Gson;

import java.util.ArrayList;

public class UserprofileFragment extends Fragment {

    private FragmentUserprofileBinding mUserprofileBinding;
    private FragmentProfileLoginBinding mProfileLoginBinding;
    private ProfileItemClickListener itemClickListener;
    private SharedPreferences mPrefs;
    private com.example.primegen.signup.User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        mUserprofileBinding = FragmentUserprofileBinding.inflate(inflater);

        //mProfileLoginBinding = FragmentProfileLoginBinding.inflate(inflater);

        mPrefs = requireActivity().getSharedPreferences("user_values", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        user = gson.fromJson(json, com.example.primegen.signup.User.class);

        itemClickListener = position -> {
            if (position == 0) {
                setSharedPreference();
                //Navigation.findNavController(mUserprofileBinding.getRoot()).navigate(R.id.action_appointment);
                Navigation.findNavController(mUserprofileBinding.getRoot()).navigate(R.id.action_my_account);
            } else if (position == 1) {
                setSharedPreference();
                Navigation.findNavController(mUserprofileBinding.getRoot()).navigate(R.id.action_my_address);
               // Navigation.findNavController(mUserprofileBinding.getRoot()).navigate(R.id.action_home_collection);
            } else if (position == 2) {
                Navigation.findNavController(mUserprofileBinding.getRoot()).navigate(R.id.action_my_test);
            } else if (position == 3) {
                Navigation.findNavController(mUserprofileBinding.getRoot()).navigate(R.id.action_my_payment);

                //Navigation.findNavController(mUserprofileBinding.getRoot()).navigate(R.id.action_my_report);
            } else if(position == 4){
                setSharedPreference();
                Navigation.findNavController(mUserprofileBinding.getRoot()).navigate(R.id.action_settings);

            }else {
                Navigation.findNavController(mUserprofileBinding.getRoot()).navigate(R.id.action_family);
            }
        };

        //setLoginUser();
        getUserList();

//        if(user != null){
//            return mUserprofileBinding.getRoot();
//        }else {
//            return mProfileLoginBinding.getRoot();
//        }
        return mUserprofileBinding.getRoot();
    }

    private void setSharedPreference() {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson1 = new Gson();
        String json1 = gson1.toJson(user);
        prefsEditor.putString("MyObject", json1);
        prefsEditor.commit();
    }

    private void getUserList() {

        if(user !=null) {

            mUserprofileBinding.userName.setText(user.getCustomerName());
            mUserprofileBinding.userEmail.setText(user.getCustomerEmail());
        }

        ArrayList<User> userList = new ArrayList<>();

//        User d1 = new User();
//        d1.setName("Appointments");
//        d1.setImage(R.drawable.ic_calenter);
//
//        User d2 = new User();
//        d2.setName("Home Collection");
//        d2.setImage(R.drawable.ic_home);



//        User d4 = new User();
//        d4.setName("My Reports");
//       d4.setImage(R.drawable.ic_report);

        User d1 = new User();
        d1.setName("My Account");
        d1.setImage(R.drawable.ic_user_edit);

        User d2 = new User();
        d2.setName("Address Book");
        d2.setImage(R.drawable.ic_address);

        User d3 = new User();
        d3.setName("Booking History");
        d3.setImage(R.drawable.ic_user_test);

        User d4 = new User();
        d4.setName("Payment");
        d4.setImage(R.drawable.ic_card);

        User d5 = new User();
        d5.setName("Settings");
        d5.setImage(R.drawable.ic_settings);

        User d6 = new User();
        d6.setName("Add Family");
        d6.setImage(R.drawable.user_icon);

        userList.add(d1);
        userList.add(d2);
        userList.add(d3);
        userList.add(d4);
        userList.add(d5);
        userList.add(d6);


        mUserprofileBinding.rvUserDetails.setLayoutManager(new GridLayoutManager(requireActivity(), 2));
        UserProfileAdapter profileAdapter = new UserProfileAdapter(requireActivity(), userList, itemClickListener);
        mUserprofileBinding.rvUserDetails.setAdapter(profileAdapter);


        mUserprofileBinding.cvCart.setOnClickListener(v ->
                Navigation.findNavController(mUserprofileBinding.getRoot()).navigate(R.id.action_add_to_cart));
        mUserprofileBinding.back.setOnClickListener(v ->
                Navigation.findNavController(mUserprofileBinding.getRoot()).navigate(R.id.action_home));
    }

    private void setLoginUser(){

        mProfileLoginBinding.btnGoToLogin.setOnClickListener(v ->
                startActivity(new Intent(requireActivity(), LoginActivity.class)));

    }
}