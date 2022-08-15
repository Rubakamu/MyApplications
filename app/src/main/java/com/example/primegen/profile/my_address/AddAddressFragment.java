package com.example.primegen.profile.my_address;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.primegen.R;
import com.example.primegen.databinding.FragmentAddAddressBinding;
import com.example.primegen.viewmodel.PrimeViewModel;
import com.google.gson.Gson;

public class AddAddressFragment extends Fragment {

    private FragmentAddAddressBinding mAddAddressBinding;
    private PrimeViewModel viewModel;
    private SharedPreferences mPrefs;
    private com.example.primegen.signup.User user;
    private String[] addressType = {"Choose One", "Home", "Office"};
    private String type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAddAddressBinding = FragmentAddAddressBinding.inflate(inflater);
        viewModel = new ViewModelProvider(this).get(PrimeViewModel.class);

        mPrefs = requireActivity().getSharedPreferences("user_values", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        user = gson.fromJson(json, com.example.primegen.signup.User.class);

        mAddAddressBinding.back.setOnClickListener(v -> getActivity().onBackPressed());

        setType();
        addNewAddress();
        return mAddAddressBinding.getRoot();
    }

    private void addNewAddress() {

        mAddAddressBinding.btnAddAddress.setOnClickListener(v -> {

            String address1 = mAddAddressBinding.edtAddress1.getText().toString();
            String address2 = mAddAddressBinding.edtAddress2.getText().toString();
            String city = mAddAddressBinding.edtCity.getText().toString();
            String state = mAddAddressBinding.edtState.getText().toString();
            String landmark = mAddAddressBinding.edtLandmark.getText().toString();
            String pincode = mAddAddressBinding.edtPincode.getText().toString();
            String addressLabel = mAddAddressBinding.edtAddressLabel.getText().toString();


            user.setCustomerAddressline1(address1);
            user.setCustomerAddressline2(address2);
            user.setCustomerCity(city);
            user.setCustomerState(state);
            user.setCustomerLandmark(landmark);
            user.setCustomerPincode(pincode);

            if (!TextUtils.isEmpty(address1) && !TextUtils.isEmpty(address2)
                    && !TextUtils.isEmpty(city) && !TextUtils.isEmpty(state) && !TextUtils.isEmpty(landmark)
                    && !TextUtils.isEmpty(pincode) && !TextUtils.isEmpty(addressLabel)) {

                viewModel.addAddress(user.getCustomerID(), user.getCustomerName(), user.getCustomerUsername(), user.getCustomerEmail(), address1,
                        address2, city, state, landmark, pincode, type, addressLabel, "", "", user.getCustomerGender(),
                        user.getCustomerDOB(), "").observe(requireActivity(), addressBookResponse -> {

                    if (addressBookResponse.getMessage().equals("Success..!")) {
                        SharedPreferences.Editor prefsEditor = mPrefs.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(user);
                        prefsEditor.putString("MyObject", json);
                        prefsEditor.commit();
                        Navigation.findNavController(mAddAddressBinding.getRoot()).navigate(R.id.action_my_address);
                    } else if (addressBookResponse.getMessage().equals("Something went wrong. Please try again later...!")) {
                        Toast.makeText(requireActivity(), addressBookResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(requireActivity(), "Field", Toast.LENGTH_SHORT).show();
                    }

                });

            } else {

                mAddAddressBinding.edtAddress1.setError("enter the value");
                mAddAddressBinding.edtAddress2.setError("enter the value");
                mAddAddressBinding.edtCity.setError("enter the value");
                mAddAddressBinding.edtState.setError("enter the value");
                mAddAddressBinding.edtLandmark.setError("enter the value");
                mAddAddressBinding.edtPincode.setError("enter the value");
                mAddAddressBinding.edtAddressLabel.setError("enter the value");
            }

        });

    }

    public void clear() {
        mAddAddressBinding.edtAddress1.setText("");
        mAddAddressBinding.edtAddress2.setText("");
        mAddAddressBinding.edtCity.setText("");
        mAddAddressBinding.edtState.setText("");
        mAddAddressBinding.edtLandmark.setText("");
        mAddAddressBinding.edtPincode.setText("");
        mAddAddressBinding.edtAddressLabel.setText("");
    }

    private void setType() {

        ArrayAdapter mType = new ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, addressType);
        mType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAddAddressBinding.edtAddressType.setAdapter(mType);


        mAddAddressBinding.edtAddressType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = addressType[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}