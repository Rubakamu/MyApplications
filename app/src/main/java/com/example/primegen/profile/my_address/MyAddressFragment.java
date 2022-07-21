package com.example.primegen.profile.my_address;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.primegen.R;
import com.example.primegen.databinding.FragmentMyAddressBinding;

public class MyAddressFragment extends Fragment {

private FragmentMyAddressBinding myAddressBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myAddressBinding = FragmentMyAddressBinding.inflate(inflater);
        initView();
        setBackPress();
        return myAddressBinding.getRoot();
    }

    private void initView() {

        myAddressBinding.cvCart.setOnClickListener(v ->
                Navigation.findNavController(myAddressBinding.getRoot()).navigate(R.id.action_add_to_cart));

    }

    private void setBackPress() {
        myAddressBinding.back.setOnClickListener(v -> {
            getActivity().onBackPressed();

        });
    }
}