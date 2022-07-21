package com.example.primegen.profile.home_collection;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.primegen.R;
import com.example.primegen.databinding.FragmentHomeCollectionsBinding;

public class HomeCollectionsFragment extends Fragment {

    private FragmentHomeCollectionsBinding mCollectionsBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mCollectionsBinding = FragmentHomeCollectionsBinding.inflate(inflater);


        initView();
        setBackPress();
        return mCollectionsBinding.getRoot();


    }

    private void initView() {
        mCollectionsBinding.listLayout.tvStatus.setText("Collected");
        mCollectionsBinding.listLayout.tvStatus.setTextColor(Color.RED);

        mCollectionsBinding.cvCart.setOnClickListener(v ->
                Navigation.findNavController(mCollectionsBinding.getRoot()).navigate(R.id.action_add_to_cart));


    }

    private void setBackPress() {
        mCollectionsBinding.back.setOnClickListener(v -> {
            getActivity().onBackPressed();

        });
    }
}