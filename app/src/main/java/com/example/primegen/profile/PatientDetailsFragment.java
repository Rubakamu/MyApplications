package com.example.primegen.profile;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.primegen.R;
import com.example.primegen.base_properties.BaseResponse;
import com.example.primegen.cart.CartSingleTon;
import com.example.primegen.cart.FamilySpinnerAdapter;
import com.example.primegen.databinding.FragmentPatientDetailsBinding;
import com.example.primegen.payment.PaymentActivity;
import com.example.primegen.payment.PaymentSuccessActivity;
import com.example.primegen.profile.family.Family;
import com.example.primegen.signup.User;
import com.example.primegen.viewmodel.PrimeViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class PatientDetailsFragment extends Fragment {

    private int mYear, mMonth, mDay;
    private int mHour;
    private int mMinute;
    private boolean checked;
    private FragmentPatientDetailsBinding mPatientDetailsBinding;
    private String paymentMode;
    private SharedPreferences mPrefs;
    private com.example.primegen.signup.User user;
    private PrimeViewModel viewModel;
    private ArrayList<Family> mFamily = new ArrayList<>();
    private ArrayList<String> relative = new ArrayList<>();
    private String userRelative;
    private String testCount;
    private String total;
    private String tests;
    ArrayList<Family> algorithmItems;
    FamilySpinnerAdapter adapter;
    private String relativeId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mPatientDetailsBinding = FragmentPatientDetailsBinding.inflate(inflater);

        mPrefs = getContext().getSharedPreferences("user_values", getContext().MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        user = gson.fromJson(json, User.class);

        viewModel = new ViewModelProvider(this).get(PrimeViewModel.class);

        if (CartSingleTon.getInstance(requireActivity()).readItemCount() != 0) {
            testCount =  String.valueOf(CartSingleTon.getInstance(requireActivity()).readItemCount());
        }


        total = getArguments().getString("test_total","");
        tests = getArguments().getString("tests","");

        initView();
        setBackPress();
        setDateTime();
        setRelative();
        return mPatientDetailsBinding.getRoot();
    }

    private void initView() {
        mPatientDetailsBinding.paymentMode.setOnCheckedChangeListener(onCheckedChangeListener);

        mPatientDetailsBinding.edtFullName.setText(user.getCustomerName());
        mPatientDetailsBinding.tvEmail.setText(user.getCustomerEmail());
        mPatientDetailsBinding.tvPhone.setText(user.getCustomerPhone());
        mPatientDetailsBinding.tvAddress.setText(user.getCustomerAddressline1());
        mPatientDetailsBinding.tvAddressLine1.setText(user.getCustomerAddressline2());
        mPatientDetailsBinding.tvCity.setText(user.getCustomerCity());
        mPatientDetailsBinding.tvLandmark.setText(user.getCustomerLandmark());
        mPatientDetailsBinding.tvState.setText("Tamil Nadu");

        mPatientDetailsBinding.btnConfirm.setOnClickListener(v -> {

            String date = mPatientDetailsBinding.edtDate.getText().toString();
            String time = mPatientDetailsBinding.edtTime.getText().toString();
            String fullAddress = user.getCustomerName()+"\n"+user.getCustomerPhone()+"\n"+user.getCustomerAddressline1()
                    +"\n"+user.getCustomerAddressline2()+"\n"+user.getCustomerCity()+"\n"+user.getCustomerLandmark();


            if(paymentMode.equals("Online")) {
               Intent intent = new Intent(requireActivity(), PaymentActivity.class);
                 intent.putExtra("test_total",total);
                 intent.putExtra("tests",tests);
               startActivity(intent);

                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                Gson gson1 = new Gson();
                String json1 = gson1.toJson(user);
                prefsEditor.putString("MyObject", json1);
                prefsEditor.commit();

            }else if(paymentMode.equals("COD")){
//                viewModel.checkout(user.getCustomerID(),date+""+time, "","2",paymentMode,fullAddress,tests,testCount,testCount,total,total,"","",relativeId,"Chennai","","","","","").observe(requireActivity(), baseResponse -> {
//
//                    Log.d("Checkout",user.getCustomerID()+""+date+""+time+""+ ""+""+"1"+""+paymentMode+""+fullAddress+""+tests+""+testCount+""+total+""+total+""+total+""+""+"1"+""+"Chennai");
//                    if(baseResponse.getMessage().equals("Success")) {
//                        startActivity(new Intent(requireActivity(), PaymentSuccessActivity.class));
//                        Toast.makeText(requireActivity(), baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                    }else{
//                        Toast.makeText(requireActivity(), baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });

            }
        });

    }

    private void setRelative() {

        viewModel.familyList(user.getCustomerID()).observe(requireActivity(), familyResponse -> {

            mFamily.addAll(familyResponse.getFamily());

            for (int i = 0; i < mFamily.size(); i++) {
                Family family = mFamily.get(i);
                relative.add(family.getRelativeID() + ". " + family.getRelativeName());

                adapter = new FamilySpinnerAdapter(requireActivity(), i, mFamily);
                mPatientDetailsBinding.edtRelative.setAdapter(adapter);
            }


        mPatientDetailsBinding.edtRelative.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // userRelative = relative.get(position);

                // It returns the clicked item.
                Family clickedItem = (Family)
                        parent.getItemAtPosition(position);
                relativeId = clickedItem.getRelativeID();
                //Toast.makeText(requireActivity(), name + " selected", Toast.LENGTH_SHORT).show();

              }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        });
    }
    private void setDateTime() {

        mPatientDetailsBinding.edtDate.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);
            //show dialog
            Date currentTime = Calendar.getInstance().getTime();
            DatePickerDialog datePickerDialog = new DatePickerDialog(requireActivity(), (view, year, month, dayOfMonth) ->
                    mPatientDetailsBinding.edtDate.setText(year + "-" + (month + 1) + "-" + dayOfMonth), mYear, mMonth, mDay);
            datePickerDialog.show();
        });

        mPatientDetailsBinding.edtTime.setOnClickListener(v -> {

            final Calendar calendar = Calendar.getInstance();
            final TimePickerDialog timePickerDialog = new TimePickerDialog(requireActivity(),
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            String AM_PM;
                            if (hourOfDay >= 0 && hourOfDay < 12) {
                                AM_PM = " AM";
                            } else {
                                AM_PM = " PM";
                            }

                            mPatientDetailsBinding.edtTime.setText(hourOfDay + ":" + minute + ":" +"00"+""+ AM_PM);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        });
    }
    private void setBackPress() {
        mPatientDetailsBinding.back.setOnClickListener(v -> {
            getActivity().onBackPressed();

        });
    }
    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.online:
                    paymentMode = mPatientDetailsBinding.online.getText().toString();
                    break;
                case R.id.cash:
                    paymentMode = mPatientDetailsBinding.cash.getText().toString();
                    break;
                default:
                    break;
            }
        }
    };
}