package com.example.primegen.profile.appointments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.primegen.R;
import com.example.primegen.databinding.FragmentAppontmentsBinding;


public class AppointmentsFragment extends Fragment {

    private FragmentAppontmentsBinding mAppointmentsBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mAppointmentsBinding = FragmentAppontmentsBinding.inflate(inflater);
        initView();
        setBackPress();
        return mAppointmentsBinding.getRoot();
    }

    private void initView() {

        mAppointmentsBinding.cvCart.setOnClickListener(v ->
                Navigation.findNavController(mAppointmentsBinding.getRoot()).navigate(R.id.action_add_to_cart));

    }

    private void setBackPress() {
        mAppointmentsBinding.back.setOnClickListener(v -> {
            getActivity().onBackPressed();

        });
    }
}