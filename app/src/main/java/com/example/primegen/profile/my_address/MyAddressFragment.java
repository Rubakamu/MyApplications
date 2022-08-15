package com.example.primegen.profile.my_address;

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
import com.example.primegen.databinding.FragmentMyAddressBinding;
import com.example.primegen.viewmodel.PrimeViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MyAddressFragment extends Fragment {

    private FragmentMyAddressBinding myAddressBinding;
    private SharedPreferences mPrefs;
    private com.example.primegen.signup.User user;
    private PrimeViewModel viewModel;
    private ArrayList<Addressbook> addressList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myAddressBinding = FragmentMyAddressBinding.inflate(inflater);
        viewModel = new ViewModelProvider(this).get(PrimeViewModel.class);

        mPrefs = requireActivity().getSharedPreferences("user_values", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        user = gson.fromJson(json, com.example.primegen.signup.User.class);

        initView();
        setBackPress();
        return myAddressBinding.getRoot();
    }

    private void initView() {

        if (CartSingleTon.getInstance(requireActivity()).readItemCount() != 0) {
            myAddressBinding.tvCount.setText(String.valueOf(CartSingleTon.getInstance(requireActivity()).readItemCount()));
        }

        viewModel.addressBook(user.getCustomerID()).observe(requireActivity(), addressBookResponse -> {
            if (getActivity() != null && isAdded()) {
            if (addressBookResponse.getAddressbook() != null) {

                    addressList.addAll(addressBookResponse.getAddressbook());
                    myAddressBinding.rvAddressList.setLayoutManager(new LinearLayoutManager(getActivity()));
                    AddressAdapter addressAdapter = new AddressAdapter(requireActivity(), addressList);
                    myAddressBinding.rvAddressList.setAdapter(addressAdapter);
                }
            }
        });

        myAddressBinding.cvCart.setOnClickListener(v ->
                Navigation.findNavController(myAddressBinding.getRoot()).navigate(R.id.action_add_to_cart));

        myAddressBinding.btnAddAddress.setOnClickListener(v -> {
            setSharedPreference();
            Navigation.findNavController(myAddressBinding.getRoot()).navigate(R.id.action_add_address);
        });
    }

    private void setSharedPreference() {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson1 = new Gson();
        String json1 = gson1.toJson(user);
        prefsEditor.putString("MyObject", json1);
        prefsEditor.commit();
    }

    private void setBackPress() {
        myAddressBinding.back.setOnClickListener(v -> {
            getActivity().onBackPressed();

        });
    }
}