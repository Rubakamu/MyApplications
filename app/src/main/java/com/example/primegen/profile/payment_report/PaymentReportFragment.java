package com.example.primegen.profile.payment_report;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.primegen.R;
import com.example.primegen.databinding.FragmentPaymentReportBinding;

public class PaymentReportFragment extends Fragment {

    private FragmentPaymentReportBinding mPaymentReportBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mPaymentReportBinding = FragmentPaymentReportBinding.inflate(inflater);
        initView();
        setBackPress();
        return mPaymentReportBinding.getRoot();
    }

    private void initView() {

        mPaymentReportBinding.cvCart.setOnClickListener(v ->
                Navigation.findNavController(mPaymentReportBinding.getRoot()).navigate(R.id.action_add_to_cart));

    }

    private void setBackPress() {
        mPaymentReportBinding.back.setOnClickListener(v -> {
            getActivity().onBackPressed();

        });
    }
}