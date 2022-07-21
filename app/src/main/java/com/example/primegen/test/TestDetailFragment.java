package com.example.primegen.test;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.primegen.R;
import com.example.primegen.databinding.FragmentTestDetailBinding;


public class TestDetailFragment extends Fragment {

    private FragmentTestDetailBinding mDetailBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mDetailBinding = FragmentTestDetailBinding.inflate(inflater);

        getTestDetails();
        setBackPress();

        return mDetailBinding.getRoot();
    }

    public void getTestDetails(){

        String name =getArguments().getString("test_name","");
        String price = getArguments().getString("price","");

        mDetailBinding.tvTestName.setText(name);
        mDetailBinding.price.setText(price);
        mDetailBinding.tabLayout.addTab(mDetailBinding.tabLayout.newTab().setText("Description"));
        mDetailBinding.tabLayout.addTab(mDetailBinding.tabLayout.newTab().setText("Test Requirement"));


        mDetailBinding.btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("test_name", name);
                bundle.putString("price", price);
                Navigation.findNavController(mDetailBinding.getRoot()).navigate(R.id.action_add_to_cart,bundle);
            }
        });

        mDetailBinding.cardLayout.ivCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(mDetailBinding.getRoot()).navigate(R.id.action_add_to_cart);

            }
        });

    }

    private void setBackPress() {
        mDetailBinding.cardLayout.back.setOnClickListener(v -> {
            getActivity().onBackPressed();

        });
    }

}