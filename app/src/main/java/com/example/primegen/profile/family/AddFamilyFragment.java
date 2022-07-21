
package com.example.primegen.profile.family;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.primegen.R;
import com.example.primegen.databinding.FragmentAddFamilyBinding;

import java.util.ArrayList;

public class AddFamilyFragment extends Fragment {

    private FragmentAddFamilyBinding mFamilyBinding;
    private ArrayList<Family> mFamily = new ArrayList<>();
    private FamilyItemClickListener itemClickListener;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mFamilyBinding = FragmentAddFamilyBinding.inflate(inflater);

        itemClickListener = new FamilyItemClickListener() {
            @Override
            public void onClick(int position) {

                mFamilyBinding.edtRelationShip.setText(mFamily.get(position).getRelation());
                mFamilyBinding.edtFullName.setText(mFamily.get(position).getName());
                mFamilyBinding.edtAge.setText(mFamily.get(position).getAge());
                mFamilyBinding.edtEmail.setText(mFamily.get(position).getEmail());
                mFamilyBinding.edtMobile.setText(mFamily.get(position).getMobile());
                mFamilyBinding.edtCity.setText(mFamily.get(position).getCity());
                mFamilyBinding.edtAddress1.setText(mFamily.get(position).getAddress1());
                mFamilyBinding.edtAddress2.setText(mFamily.get(position).getAddress2());
                mFamilyBinding.edtPincode.setText(mFamily.get(position).getPinCode());
            }
        };

        initView();
        return mFamilyBinding.getRoot();

    }

    private void initView() {
        String[] countries = getResources().getStringArray(R.array.countries);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireActivity(),
                R.layout.support_simple_spinner_dropdown_item, countries);
        mFamilyBinding.edtCity.setAdapter(adapter);

        String[] relation = getResources().getStringArray(R.array.relation);


        ArrayAdapter<String> relationAdapter = new ArrayAdapter<String>(requireActivity(),
                R.layout.support_simple_spinner_dropdown_item, relation);
        mFamilyBinding.edtRelationShip.setAdapter(relationAdapter);


        mFamilyBinding.btnAdd.setOnClickListener(v -> {

            String username = mFamilyBinding.edtFullName.getText().toString();
            String age = mFamilyBinding.edtAge.getText().toString();
            String email = mFamilyBinding.edtEmail.getText().toString();
            String mobile = mFamilyBinding.edtMobile.getText().toString();
            String city = mFamilyBinding.edtCity.getText().toString();
            String address1 = mFamilyBinding.edtAddress1.getText().toString();
            String address2 = mFamilyBinding.edtAddress2.getText().toString();
            String pincode = mFamilyBinding.edtPincode.getText().toString();
            String relationShip = mFamilyBinding.edtRelationShip.getText().toString();

            if (!TextUtils.isEmpty(relationShip) && !TextUtils.isEmpty(username) && !TextUtils.isEmpty(age) && !TextUtils.isEmpty(email)
                    && !TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(city) && !TextUtils.isEmpty(address1)
                    && !TextUtils.isEmpty(address2) && !TextUtils.isEmpty(pincode)) {


                Family family = new Family();

                family.setRelation(relationShip);
                family.setName(username);
                family.setAddress1(address1);
                family.setAddress2(address2);
                family.setAge(age);
                family.setMobile(mobile);
                family.setPinCode(pincode);

                mFamily.add(family);

                mFamilyBinding.rvFamilyList.setLayoutManager(new GridLayoutManager(requireActivity(), 2));
                FamilyAdapter familyAdapter = new FamilyAdapter(requireActivity(), mFamily, itemClickListener);
                mFamilyBinding.rvFamilyList.setAdapter(familyAdapter);

                clear();

            } else {

                mFamilyBinding.edtRelationShip.setError("enter the value");
                mFamilyBinding.edtFullName.setError("enter the value");
                mFamilyBinding.edtAge.setError("enter the value");
                mFamilyBinding.edtEmail.setError("enter the value");
                mFamilyBinding.edtMobile.setError("enter the value");
                mFamilyBinding.edtCity.setError("enter the value");
                mFamilyBinding.edtAddress1.setError("enter the value");
                mFamilyBinding.edtAddress2.setError("enter the value");
                mFamilyBinding.edtPincode.setError("enter the value");
            }

        });
    }
    public void clear(){
        mFamilyBinding.edtRelationShip.setText("");
        mFamilyBinding.edtFullName.setText("");
        mFamilyBinding.edtAge.setText("");
        mFamilyBinding.edtEmail.setText("");
        mFamilyBinding.edtMobile.setText("");
        mFamilyBinding.edtCity.setText("");
        mFamilyBinding.edtAddress1.setText("");
        mFamilyBinding.edtAddress2.setText("");
        mFamilyBinding.edtPincode.setText("");
    }
}