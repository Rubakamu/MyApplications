package com.example.primegen.profile.my_report;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.primegen.R;
import com.example.primegen.cart.CartSingleTon;
import com.example.primegen.databinding.FragmentMyReportBinding;


public class MyReportFragment extends Fragment {

    private FragmentMyReportBinding myReportBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myReportBinding = FragmentMyReportBinding.inflate(inflater);

        if (CartSingleTon.getInstance(requireActivity()).readItemCount() != 0) {
            myReportBinding.tvCount.setText(String.valueOf(CartSingleTon.getInstance(requireActivity()).readItemCount()));
        }

        initView();
        setBackPress();
        return myReportBinding.getRoot();

    }

    private void initView() {

        myReportBinding.cvCart.setOnClickListener(v ->
                Navigation.findNavController(myReportBinding.getRoot()).navigate(R.id.action_add_to_cart));

    }

    private void setBackPress() {
        myReportBinding.back.setOnClickListener(v -> {
            getActivity().onBackPressed();

        });
    }
}