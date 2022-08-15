
package com.example.primegen.profile.family;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.primegen.R;
import com.example.primegen.cart.CartSingleTon;
import com.example.primegen.databinding.FragmentAddFamilyBinding;
import com.example.primegen.viewmodel.PrimeViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;

public class AddFamilyFragment extends Fragment {

    private FragmentAddFamilyBinding mFamilyBinding;
    private ArrayList<Family> mFamily = new ArrayList<>();
    private FamilyItemClickListener itemClickListener;
    private SharedPreferences mPrefs;
    private com.example.primegen.signup.User user;
    private PrimeViewModel viewModel;
    private String mRelations;
    private String gender;
    private int mYear, mMonth, mDay;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mFamilyBinding = FragmentAddFamilyBinding.inflate(inflater);

        viewModel = new ViewModelProvider(this).get(PrimeViewModel.class);
        mFamilyBinding.rgGender.setOnCheckedChangeListener(onCheckedChangeListener);

        mPrefs = requireActivity().getSharedPreferences("user_values", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        user = gson.fromJson(json, com.example.primegen.signup.User.class);

        if (CartSingleTon.getInstance(requireActivity()).readItemCount() != 0) {
            mFamilyBinding.tvCount.setText(String.valueOf(CartSingleTon.getInstance(requireActivity()).readItemCount()));
        }

        mFamilyBinding.back.setOnClickListener(v -> requireActivity().onBackPressed());

        itemClickListener = new FamilyItemClickListener() {
            @Override
            public void onClick(int position) {

            }
        };

        getFamilyList();
        initView();
        return mFamilyBinding.getRoot();

    }

    private void getFamilyList() {

        viewModel.familyList(user.getCustomerID()).observe(requireActivity(), familyResponse -> {

            if (getActivity() != null && isAdded()) {
                if (familyResponse.getFamily() != null) {
                    mFamily.addAll(familyResponse.getFamily());

                    mFamilyBinding.rvFamilyList.setLayoutManager(new GridLayoutManager(requireActivity(), 2));
                    FamilyAdapter familyAdapter = new FamilyAdapter(requireActivity(), mFamily, itemClickListener);
                    mFamilyBinding.rvFamilyList.setAdapter(familyAdapter);
                }
            }

        });


    }

    private void initView() {
        String[] relation = getResources().getStringArray(R.array.relation);

        mFamilyBinding.edtDOB.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);

            //show dialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(requireActivity(), (view, year, month, dayOfMonth) ->
                    mFamilyBinding.edtDOB.setText(year + "-" + (month + 1) + "-" + dayOfMonth), mYear, mMonth, mDay);
            datePickerDialog.show();
        });

        ArrayAdapter relationship = new ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, relation);
        relationship.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mFamilyBinding.edtRelationship.setAdapter(relationship);

        mFamilyBinding.edtRelationship.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(relation[position].equals("0")) {
                    mRelations = "0";
                }else if(relation[position].equals("Father")) {
                    mRelations = "2";
                }else if(relation[position].equals("Mother")){
                    mRelations = "3";
                }else if(relation[position].equals("Spouse")){
                    mRelations = "4";
                }else if(relation[position].equals("Brother")){
                    mRelations = "5";
                }else if(relation[position].equals("Sister")){
                    mRelations = "6";
                }else if(relation[position].equals("Son")){
                    mRelations = "7";
                }else if(relation[position].equals("Daughter")){
                    mRelations = "8";
                }else if(relation[position].equals("Uncle")){
                    mRelations = "9";
                }else if(relation[position].equals("Aunty")){
                    mRelations = "10";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mFamilyBinding.btnAdd.setOnClickListener(v -> {

            String username = mFamilyBinding.edtFullName.getText().toString();
            String mobile = mFamilyBinding.edtMobile.getText().toString();
            String dob = mFamilyBinding.edtDOB.getText().toString();

            if (!TextUtils.isEmpty(mRelations) && !TextUtils.isEmpty(username) && !TextUtils.isEmpty(mobile)) {

                viewModel.addFamilyMember(user.getCustomerID(), username, mobile, dob, gender, mRelations).observe(requireActivity(), new Observer<FamilyResponse>() {
                    @Override
                    public void onChanged(FamilyResponse familyResponse) {
                        if (familyResponse.getMessage().equals("Success")) {

                            Toast.makeText(requireActivity(), familyResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            //clear();
                        }


                    }
                });
            }
//            } else {
//                mFamilyBinding.edtFullName.setError("enter the value");
//                mFamilyBinding.edtMobile.setError("enter the value");
//            }

        });
    }
//
//    public void clear() {
//        mFamilyBinding.edtFullName.setText("");
//        mFamilyBinding.edtMobile.setText("");

   // }

    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.male:
                    gender = mFamilyBinding.male.getText().toString();
                    break;
                case R.id.female:
                    gender = mFamilyBinding.female.getText().toString();
                    break;
                default:
                    break;
            }
        }
    };
}