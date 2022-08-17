package com.example.primegen.cart;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.primegen.R;
import com.example.primegen.base_properties.Branch;
import com.example.primegen.databinding.FragmentAddToCartBinding;
import com.example.primegen.payment.PaymentActivity;
import com.example.primegen.payment.PaymentSuccessActivity;
import com.example.primegen.profile.family.Family;
import com.example.primegen.signup.User;
import com.example.primegen.test.Test;
import com.example.primegen.viewmodel.PrimeViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddToCartFragment extends Fragment {

    private FragmentAddToCartBinding mCartBinding;
    private boolean isCartLogin = false;
    private List<Test> testList = new ArrayList<>();
    CartClickListener cartClickListener;
    private CartAdapter cartAdapter;
    private SharedPreferences mPrefs;
    private com.example.primegen.signup.User user;
    private String testCount;
    private String location;
    private ArrayList<String> tests = new ArrayList<>();
    private String locationId;
    private String paymentMode;
    private PrimeViewModel viewModel;
    private ArrayList<Family> mFamily = new ArrayList<>();
    private ArrayList<String> relative = new ArrayList<>();
    FamilySpinnerAdapter adapter;
    private String relativeId;
    private int mYear, mMonth, mDay;
    private int mHour;
    private int mMinute;
    private double total;
    String testDetails;
    private List<Branch> branchList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mCartBinding = FragmentAddToCartBinding.inflate(inflater);

        viewModel = new ViewModelProvider(this).get(PrimeViewModel.class);

        mPrefs = getContext().getSharedPreferences("user_values", getContext().MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        user = gson.fromJson(json, User.class);

        if (!CartSingleTon.getInstance(requireActivity()).read().isEmpty()){
            mCartBinding.emptyLayout.setVisibility(View.GONE);
            mCartBinding.bookingLayout.setVisibility(View.VISIBLE);

        }else {
            mCartBinding.emptyLayout.setVisibility(View.VISIBLE);
            mCartBinding.bookingLayout.setVisibility(View.GONE);
        }
        if (getArguments() != null) {
            location = getArguments().getString("location");
            locationId = getArguments().getString("locationId");
            mCartBinding.tvLocationName.setText(location);
        }
        setAdapter();
        initView();
        setDateTime();
        setRelative();
        setLocation();
        return mCartBinding.getRoot();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void setAdapter() {
        cartClickListener = position -> {
            CartSingleTon.getInstance(requireActivity())
                    .remove(testList.get(position).getTestprofileID());
            testList.remove(position);
            Navigation.findNavController(mCartBinding.getRoot()).navigate(R.id.action_add_to_cart);
            cartAdapter.notifyDataSetChanged();

        };
        mCartBinding.rvTestList.setLayoutManager(new LinearLayoutManager(requireActivity()));
        cartAdapter = new CartAdapter(requireActivity(), testList, cartClickListener);
        mCartBinding.rvTestList.setAdapter(cartAdapter);

    }


    @SuppressLint("ApplySharedPref")
    @Override
    public void onStop() {
        super.onStop();
        CartSingleTon.getInstance(requireActivity()).save();

    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onResume() {
        super.onResume();
        testList.clear();
        testList.addAll(CartSingleTon.getInstance(requireActivity()).read());

        total = 0;
        for (int i = 0; i<testList.size(); i++)
        {
            total += Double.parseDouble(testList.get(i).getTestprofileOfferPrice());
        }
        mCartBinding.tvOrderAmt.setText(String.valueOf(total));
        mCartBinding.tvTotalAmt.setText(String.valueOf(total));

        cartAdapter.notifyDataSetChanged();


    }

    private void initView() {




        mCartBinding.btnBookings.setOnClickListener(v ->
                Navigation.findNavController(mCartBinding.getRoot()).navigate(R.id.action_test_list));

        mCartBinding.paymentMode.setOnCheckedChangeListener(onCheckedChangeListener);

        mCartBinding.edtFullName.setText(user.getCustomerName());
        mCartBinding.tvEmail.setText(user.getCustomerEmail());
        mCartBinding.tvPhone.setText(user.getCustomerPhone());
        String fullAddress = user.getCustomerAddressline1() + "\n" + user.getCustomerAddressline2() + "\n" + user.getCustomerCity() + "\n" + user.getCustomerLandmark();

        mCartBinding.tvAddress.setText(fullAddress);
        mCartBinding.tvAddressLine1.setText(user.getCustomerAddressline2());
        mCartBinding.tvCity.setText(user.getCustomerCity());
        mCartBinding.tvLandmark.setText(user.getCustomerLandmark());
        //mCartBinding.tvState.setText("Tamil Nadu");

//        mCartBinding.tvAddress.getText().toString().isEmpty();
        if (user.getCustomerAddressline1().isEmpty()) {
            mCartBinding.cardaddAddress.setVisibility(View.VISIBLE);
            mCartBinding.cardPersonal.setVisibility(View.GONE);
        } else{
            mCartBinding.cardaddAddress.setVisibility(View.GONE);
            mCartBinding.cardPersonal.setVisibility(View.VISIBLE);
        }

        mCartBinding.cardaddAddress.setOnClickListener(v -> {
            setSharedPreferenceValue();
            Navigation.findNavController(mCartBinding.getRoot()).navigate(R.id.action_my_address);
        });
        mCartBinding.btnSelect.setOnClickListener(v -> {

            String date = mCartBinding.edtDate.getText().toString();
            String time = mCartBinding.edtTime.getText().toString();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < testList.size(); i++) {
                Test test = testList.get(i);
                sb.append(test.getTestprofileID());
                sb.append("-");
                sb.append("1");
                sb.append(",");
            }
            String sel_cat = String.valueOf(sb);
            testDetails = sel_cat.substring(0, sel_cat.length() - 1);


            ArrayList<String> testId = new ArrayList<>();
            for (int i = 0; i < testList.size(); i++) {
                Test test = testList.get(i);
                testId.add(test.getTestprofileID());

            }

            ArrayList<String> testPrice = new ArrayList<>();
            for (int i = 0; i < testList.size(); i++) {
                Test test = testList.get(i);
                testPrice.add(test.getTestprofileOfferPrice());
            }

            ArrayList<String> torp = new ArrayList<>();
            for (int i = 0; i < testList.size(); i++) {
                Test test = testList.get(i);
                torp.add(test.getIsTestOrProfile());
            }

            String datetime = date + " " + time;

            if (CartSingleTon.getInstance(requireActivity()).readItemCount() != 0) {
                testCount = String.valueOf(CartSingleTon.getInstance(requireActivity()).readItemCount());
            }
            if (!TextUtils.isEmpty(paymentMode) && !TextUtils.isEmpty(date) && !TextUtils.isEmpty(time) && !TextUtils.isEmpty(locationId) && !TextUtils.isEmpty(fullAddress)) {

                if (paymentMode.equals("Online")) {

                    viewModel.checkout(user.getCustomerID(), datetime, "", "2", paymentMode, fullAddress, testDetails, testCount,
                            testCount, String.valueOf(total), String.valueOf(total), "", "", relativeId,
                            locationId, testId, torp, testPrice, testPrice, torp).observe(requireActivity(), baseResponse -> {

                        Log.d("Checkout", user.getCustomerID() + "" + date + "" + time + "" + "" + "" + "1" + "" + paymentMode + "" + fullAddress + "" + sel_cat + "" + testCount + "" + total + "" + total + "" + total + "" + "" + "1" + "" + "Chennai");
                        if (baseResponse.getMessage().equals("Success")) {
                            //startActivity(new Intent(requireActivity(), PaymentSuccessActivity.class));
                            Intent intent = new Intent(requireActivity(), PaymentActivity.class);
                            intent.putExtra("test_total", total);
                            intent.putExtra("tests", tests);
                            startActivity(intent);

                            SharedPreferences.Editor prefsEditor = mPrefs.edit();
                            Gson gson1 = new Gson();
                            String json1 = gson1.toJson(user);
                            prefsEditor.putString("MyObject", json1);
                            prefsEditor.commit();
                            Toast.makeText(requireActivity(), baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(requireActivity(), baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


                } else if (paymentMode.equals("COD")) {
                    viewModel.checkout(user.getCustomerID(), datetime, "", "2", paymentMode, fullAddress, testDetails,
                            testCount, testCount, String.valueOf(total), String.valueOf(total), "",
                            "", relativeId, locationId, testId, torp, testPrice, testPrice, torp).observe(requireActivity(), baseResponse -> {

                        Log.d("Checkout", user.getCustomerID() + "" + date + "" + time + "" + "" + "" + "1" + "" + paymentMode + "" + fullAddress + "" + sel_cat + "" + testCount + "" + total + "" + total + "" + total + "" + "" + relativeId + "" + locationId + "" + testId + "" + torp + "" + testPrice + "" + testPrice + "" + torp);
                        if (baseResponse.getMessage().equals("Success")) {
                            startActivity(new Intent(requireActivity(), PaymentSuccessActivity.class));
                            Toast.makeText(requireActivity(), baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(requireActivity(), baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            } else if (TextUtils.isEmpty(date) && TextUtils.isEmpty(time) && TextUtils.isEmpty(paymentMode)) {
                mCartBinding.tvDateText.setTextColor(Color.RED);
                mCartBinding.tvPayment.setTextColor(Color.RED);
            } else if (TextUtils.isEmpty(locationId)) {
                mCartBinding.tvLocationError.setText("Select Location");
            } else if (!TextUtils.isEmpty(locationId)) {
                mCartBinding.tvLocationError.setText(" ");
            }
//            else if () {
//
//                //Toast.makeText(requireActivity(), "Select payment mode", Toast.LENGTH_SHORT).show();
//            }

        });


    }

    private void setRelative() {

        viewModel.familyList(user.getCustomerID()).observe(requireActivity(), familyResponse -> {

            if (getActivity() != null && isAdded() && isVisible()) {
                if (familyResponse.getFamily() != null) {
                    mFamily.addAll(familyResponse.getFamily());
                }

                for (int i = 0; i < mFamily.size(); i++) {
                    Family family = mFamily.get(i);
                    relative.add(family.getRelativeID() + ". " + family.getRelativeName());

                    adapter = new FamilySpinnerAdapter(requireActivity(), i, mFamily);
                    mCartBinding.edtRelative.setAdapter(adapter);
                }
            }

            mCartBinding.edtRelative.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Family clickedItem = (Family)
                            parent.getItemAtPosition(position);
                    relativeId = clickedItem.getRelativeID();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        });
    }

    private void setDateTime() {

        mCartBinding.edtDate.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);
            //show dialog
            Date currentTime = Calendar.getInstance().getTime();
            DatePickerDialog datePickerDialog = new DatePickerDialog(requireActivity(), (view, year, month, dayOfMonth) ->
                    mCartBinding.edtDate.setText(year + "-" + (month + 1) + "-" + dayOfMonth), mYear, mMonth, mDay);
            datePickerDialog.show();
        });

        mCartBinding.edtTime.setOnClickListener(v -> {

            final Calendar calendar = Calendar.getInstance();
            final TimePickerDialog timePickerDialog = new TimePickerDialog(requireActivity(),
                    (view, hourOfDay, minute) -> {
                        String AM_PM;
                        if (hourOfDay >= 0 && hourOfDay < 12) {
                            AM_PM = " AM";
                        } else {
                            AM_PM = " PM";
                        }
                        mCartBinding.edtTime.setText(hourOfDay + ":" + minute + ":" + "00" + "" + AM_PM);
                    }, mHour, mMinute, false);
            timePickerDialog.show();

//            Date date = new Date();
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(date);
//            calendar.set(Calendar.HOUR_OF_DAY, 6);// for 6 hour
//            calendar.set(Calendar.MINUTE, 0);// for 0 min
//            calendar.set(Calendar.SECOND, 0);// for 0 sec
//            System.out.println(calendar.getTime());
//            mCartBinding.edtTime.setText(String.valueOf(calendar.getTime()));
        });
    }

    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.online:
                    paymentMode = mCartBinding.online.getText().toString();
                    break;
                case R.id.cash:
                    paymentMode = mCartBinding.cash.getText().toString();
                    break;
                default:
                    break;
            }
        }
    };

    public void setLocation() {


        viewModel.getAllBrachData().observe(requireActivity(), branchResponse -> {

            branchList = new ArrayList<>();

            if (getActivity() != null && isAdded() && isVisible()) {
                branchList.addAll(branchResponse.getBranch());
            }
            mCartBinding.cardLocation.setOnClickListener(v -> {

                AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
                builder.setTitle("Select Location");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(requireActivity(), android.R.layout.simple_list_item_1);
                arrayAdapter.clear();
                for (int i = 0; i < branchList.size(); i++) {

                    Log.i("test_name", branchList.get(i).getBranchName());
                    Branch branches = branchList.get(i);
                    arrayAdapter.add(branches.getBranchID() + ". " + branches.getBranchName());
                }
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Toast.makeText(requireActivity(), branchList.get(which).getBranchName(), Toast.LENGTH_LONG).show();
                        mCartBinding.tvLocationName.setText(branchList.get(which).getBranchName());
                        locationId = branchList.get(which).getBranchID();
                    }
                });
                builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();


            });

        });


    }

    public void setSharedPreferenceValue() {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson1 = new Gson();
        String json1 = gson1.toJson(user);
        prefsEditor.putString("MyObject", json1);
        prefsEditor.commit();
    }
}
