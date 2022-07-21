package com.example.primegen.cart;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.primegen.R;
import com.example.primegen.databinding.FragmentAddToCartBinding;
import com.example.primegen.signup.User;
import com.example.primegen.test.Test;
import com.google.gson.Gson;

import java.util.ArrayList;

public class AddToCartFragment extends Fragment {

    private FragmentAddToCartBinding mCartBinding;
    private boolean isCartLogin = false;
    private SharedPreferences mPrefs;
    private User user;
    private ArrayList<Test> testList = new ArrayList<>();
    CartClickListener cartClickListener;
    private CartSingleTon cartSingleTon;
    private Test test;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mCartBinding = FragmentAddToCartBinding.inflate(inflater);


        setAdapder();

        cartClickListener = new CartClickListener() {
            @Override
            public void onClick(int position) {

                cartSingleTon.remove(testList.get(position).getTestprofileID());

            }
        };

        init();
        setBackPress();

        return mCartBinding.getRoot();

    }

    private void setAdapder() {

        cartSingleTon = CartSingleTon.getInstance();

        testList.addAll(cartSingleTon.getTestList());

        mCartBinding.rvTestList.setLayoutManager(new LinearLayoutManager(requireActivity()));
        CartAdapter cartAdapter = new CartAdapter(requireActivity(), testList, cartClickListener);
        mCartBinding.rvTestList.setAdapter(cartAdapter);
    }

    private void init() {

//        if (getArguments() != null) {
//            String name = getArguments().getString("test_name", "");
//            String price = getArguments().getString("price", "");
//
//            mCartBinding.testCard.setVisibility(View.VISIBLE);
//            mCartBinding.tvTestName.setText(name);
//            mCartBinding.price.setText(price);
//        } else {
//            mCartBinding.testCard.setVisibility(View.GONE);
//        }
        mCartBinding.btnSelect.setOnClickListener(v -> {

            // if (user != null) {
            Navigation.findNavController(mCartBinding.getRoot()).navigate(R.id.action_patient_details);
            //} else {
//                Intent intent = new Intent(requireActivity(), LoginActivity.class);
//                intent.putExtra("cart_login", isCartLogin);
//                startActivity(intent);
//            }
        });


        mCartBinding.ivSearchDelete.setOnClickListener(v ->
                mCartBinding.edtSearch.setText(""));

    }

    private void setBackPress() {
        mCartBinding.back.setOnClickListener(v -> {
            getActivity().onBackPressed();

        });
    }

    @Override
    public void onStop() {
        super.onStop();
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cartSingleTon);
        prefsEditor.putString("cart", json);
        prefsEditor.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPrefs = requireActivity().getSharedPreferences("test_value", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("cart", "");
        cartSingleTon = gson.fromJson(json, CartSingleTon.class);

    }
}