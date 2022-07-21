package com.example.primegen.profile.my_test;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.primegen.R;
import com.example.primegen.databinding.FragmentMyTestBinding;

public class MyTestFragment extends Fragment {

    private FragmentMyTestBinding myTestBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myTestBinding = FragmentMyTestBinding.inflate(inflater);
        initView();
        setBackPress();
        return myTestBinding.getRoot();
    }

    private void initView() {


        myTestBinding.cvCart.setOnClickListener(v ->
                Navigation.findNavController(myTestBinding.getRoot()).navigate(R.id.action_add_to_cart));

    }

    private void setBackPress() {
        myTestBinding.back.setOnClickListener(v -> {
            getActivity().onBackPressed();

        });
    }
}