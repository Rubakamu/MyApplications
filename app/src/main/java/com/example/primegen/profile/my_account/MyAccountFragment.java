package com.example.primegen.profile.my_account;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.primegen.R;
import com.example.primegen.databinding.FragmentMyAccountBinding;
import com.example.primegen.login.OtpVerificationActivity;
import com.example.primegen.signup.RegisterActivity;
import com.example.primegen.viewmodel.PrimeViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class MyAccountFragment extends Fragment{

    private FragmentMyAccountBinding myAccountBinding;
    private SharedPreferences mPrefs;
    private com.example.primegen.signup.User user;
    private String[] title = {"Mr", "Mrs", "Miss", "Ms"};
    private String[] blood = {"Select a Bloodgroup", "O+ive", "O-ive", "A+ive", "A-ive", "B+ive", "B-ive", "AB+ive", "AB-ive", "Rh null", "HH"};

    private PrimeViewModel viewModel;
    private String userTitle;
    private int mYear, mMonth, mDay;
    private String gender;
    private String mBloodGroup;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myAccountBinding = FragmentMyAccountBinding.inflate(inflater);
        viewModel = new ViewModelProvider(this).get(PrimeViewModel.class);

        myAccountBinding.rgGender.setOnCheckedChangeListener(onCheckedChangeListener);
        initView();
        updateProfile();
        setBackPress();

        return myAccountBinding.getRoot();
    }

    private void initView() {
        myAccountBinding.cvCart.setOnClickListener(v ->
                Navigation.findNavController(myAccountBinding.getRoot()).navigate(R.id.action_add_to_cart));

        myAccountBinding.edtDOB.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);

            //show dialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(requireActivity(), (view, year, month, dayOfMonth) ->
                    myAccountBinding.edtDOB.setText(dayOfMonth + "-" + (month + 1) + "-" + year), mYear, mMonth, mDay);
            datePickerDialog.show();
        });

        setBloodGroup();
        setTitle();

    }

    private void setTitle(){

        ArrayAdapter mTitle = new ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, title);
        mTitle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myAccountBinding.edtTitle.setAdapter(mTitle);


        myAccountBinding.edtTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userTitle = title[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void setBloodGroup() {

        ArrayAdapter bloodGroup = new ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, blood);
        bloodGroup.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myAccountBinding.edtBloodGroup.setAdapter(bloodGroup);

//        int index=0;
//
//        for(int i=0;i < title.length;i++){
//            if(title[i].equals(bgFromServer)){
//                index=i;
//                break;
//            }
//        }
//
        myAccountBinding.edtBloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mBloodGroup = blood[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void setBackPress() {
        myAccountBinding.back.setOnClickListener(v -> {
            getActivity().onBackPressed();

        });

    }


    private void updateProfile() {

        myAccountBinding.btnUpdate.setOnClickListener(v -> {

            String fullName = myAccountBinding.edtFullName.getText().toString();
            String userName = myAccountBinding.edtUserName.getText().toString();
            String password = myAccountBinding.edtPassword.getText().toString();
            String email = myAccountBinding.edtEmail.getText().toString();
            String dob = myAccountBinding.edtDOB.getText().toString();

            user.setCustomerName(fullName);
            user.setCustomerTitle(userTitle);
            user.setCustomerEmail(email);
            user.setCustomerPassword(password);
            user.setCustomerUsername(userName);
            user.setCustomerGender(gender);
            user.setCustomerBloodGroup(mBloodGroup);
            user.setCustomerDOB(dob);

        viewModel.updateUser(user.getCustomerID(), userTitle,fullName,"",
                userName,password,email,dob,"",gender,mBloodGroup).observe(requireActivity(), baseResponse -> {

            if (baseResponse.getMessage().equals("Something went wrong. Please try again later...!")){
                Toast.makeText(requireActivity(),baseResponse.getMessage(),Toast.LENGTH_SHORT).show();

            }else if(baseResponse.getMessage().equals("Updated Successfully...!")){
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(user);
                    prefsEditor.putString("MyObject", json);
                    prefsEditor.commit();
                    Navigation.findNavController(myAccountBinding.getRoot()).navigate(R.id.action_add_to_cart);
                Toast.makeText(requireActivity(),baseResponse.getMessage(),Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(requireActivity(),"Failed",Toast.LENGTH_SHORT).show();
            }

        });

        });

    }
    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.male:
                    gender = myAccountBinding.male.getText().toString();
                    //Toast.makeText(requireActivity(), myAccountBinding.male.getText(), Toast.LENGTH_SHORT).show();

                    break;
                case R.id.female:
                    // Write your code here
                    gender = myAccountBinding.female.getText().toString();
                    //Toast.makeText(requireActivity(), myAccountBinding.female.getText(), Toast.LENGTH_SHORT).show();


                    break;
                case R.id.others:
                    // Write your code here
                    gender = myAccountBinding.others.getText().toString();
                   // Toast.makeText(requireActivity(), myAccountBinding.others.getText(), Toast.LENGTH_SHORT).show();

                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        mPrefs = requireActivity().getSharedPreferences("user_values", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        user = gson.fromJson(json, com.example.primegen.signup.User.class);

        myAccountBinding.edtFullName.setText(user.getCustomerName());
        myAccountBinding.edtEmail.setText(user.getCustomerEmail());
        myAccountBinding.edtUserName.setText(user.getCustomerUsername());
        myAccountBinding.edtPassword.setText(user.getCustomerPassword());
        myAccountBinding.edtDOB.setText(user.getCustomerDOB());

if(user.getCustomerTitle() !=null) {
    String bgFromServer = user.getCustomerTitle();

    int index = 0;

    for (int i = 0; i < title.length; i++) {
        if (title[i].equals(bgFromServer)) {
            index = i;
            break;
        }
    }
    myAccountBinding.edtTitle.setSelection(index);
}



    }


}