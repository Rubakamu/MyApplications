package com.example.primegen.test;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.primegen.R;
import com.example.primegen.cart.CartSingleTon;
import com.example.primegen.databinding.FragmentTestBinding;
import com.example.primegen.signup.User;
import com.example.primegen.viewmodel.PrimeViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;

public class TestFragment extends Fragment {

    private FragmentTestBinding mTestBinding;
    private PrimeViewModel viewModel;
    private TestClickListener testClickListener;
    private ArrayList<Test> testList = new ArrayList<>();
    private SharedPreferences mPrefs;
    private User user;
    private Test test;
    private CartSingleTon cartSingleTon;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mTestBinding = FragmentTestBinding.inflate(inflater);


        mPrefs = requireActivity().getSharedPreferences("test_value", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("cart", "");
        cartSingleTon = gson.fromJson(json, CartSingleTon.class);
        viewModel = new ViewModelProvider(this).get(PrimeViewModel.class);

        testClickListener = position -> {

            cartSingleTon = CartSingleTon.getInstance();
            cartSingleTon.setTestList(testList.get(position));


        };

        getTestList();
        setBackPress();

        return mTestBinding.getRoot();
    }

    private void getTestList() {

        viewModel.getAllTestData().observe(requireActivity(), testResponse -> {

            testList.addAll(testResponse.getTest());

            mTestBinding.rvTestList.setLayoutManager(new LinearLayoutManager(requireActivity()));
            TestAdapter testAdapter = new TestAdapter(requireActivity(), testList, testClickListener);
            mTestBinding.rvTestList.setAdapter(testAdapter);

        });
        mTestBinding.cvCart.setOnClickListener(v -> {

            Navigation.findNavController(mTestBinding.getRoot()).navigate(R.id.action_add_to_cart);
        });


        mTestBinding.ivSearchDelete.setOnClickListener(v ->
                mTestBinding.edtSearch.setText(""));

    }

    private void setBackPress() {
        mTestBinding.back.setOnClickListener(v -> {
            getActivity().onBackPressed();

        });
    }
//    public void setSharedPreferenceValue(){
//        SharedPreferences.Editor prefsEditor = mPrefs.edit();
//        Gson gson1 = new Gson();
//        String json1 = gson1.toJson(user);
//        prefsEditor.putString("MyObject", json1);
//        prefsEditor.commit();
//    }



    @Override
    public void onResume() {
        super.onResume();

        mPrefs = requireActivity().getSharedPreferences("test_value", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("cart", "");
        cartSingleTon = gson.fromJson(json, CartSingleTon.class);
    }
}