package com.example.primegen.profile.my_account;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.primegen.R;
import com.example.primegen.cart.CartSingleTon;
import com.example.primegen.databinding.FragmentMyAccountBinding;
import com.example.primegen.viewmodel.PrimeViewModel;
import com.google.gson.Gson;

import java.util.Calendar;

public class MyAccountFragment extends Fragment {

    private FragmentMyAccountBinding myAccountBinding;
    private SharedPreferences mPrefs;
    private com.example.primegen.signup.User user;
    private String[] title = {"Mr", "Mrs", "Miss", "Ms"};
    private String[] blood = {"Select a Bloodgroup", "O+ve",  "A+ve", "A-ve", "B+ve", "B-ve","O-ve", "AB+ve", "AB-ve", "Rh null", "HH"};

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
        if (CartSingleTon.getInstance(requireActivity()).readItemCount() != 0) {
            myAccountBinding.tvCount.setText(String.valueOf(CartSingleTon.getInstance(requireActivity()).readItemCount()));
        }


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
                    myAccountBinding.edtDOB.setText(year + "-" + (month + 1) + "-" + dayOfMonth), mYear, mMonth, mDay);
            datePickerDialog.show();
        });

        setBloodGroup();
        setTitle();

    }

    private void setTitle() {

        ArrayAdapter mTitle = new ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, title);
        mTitle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myAccountBinding.edtTitle.setAdapter(mTitle);
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

        myAccountBinding.edtBloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 1) {
                    mBloodGroup = "1";
                } else if(position == 2) {
                    mBloodGroup = "2";
                } else if(position == 3) {
                    mBloodGroup = "3";
                } else if(position == 4) {
                    mBloodGroup = "4";
                } else if(position == 5) {
                    mBloodGroup = "5";
                } else if(position == 6) {
                    mBloodGroup = "6";
                } else if(position == 7) {
                    mBloodGroup = "7";
                } else if(position == 8) {
                    mBloodGroup = "8";
                } else if(position == 9) {
                    mBloodGroup = "9";
                } else if(position == 10) {
                    mBloodGroup = "10";
                }
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

            viewModel.updateUser(user.getCustomerID(), userTitle, fullName, "",
                    userName, password, email, dob, "", gender, mBloodGroup).observe(requireActivity(), baseResponse -> {

                Log.d("Update Data", user.getCustomerID() + "" + userTitle + "" + fullName + "" +
                        userName + "" + password + "" + email + "" + dob + "" + gender + "" + mBloodGroup);

                if (baseResponse.getMessage().equals("Something went wrong. Please try again later...!")) {
                    Toast.makeText(requireActivity(), baseResponse.getMessage(), Toast.LENGTH_SHORT).show();

                } else if (baseResponse.getMessage().equals("Updated Successfully...!")) {
                    SharedPreferences.Editor prefsEditor = mPrefs.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(user);
                    prefsEditor.putString("MyObject", json);
                    prefsEditor.commit();
                    //Navigation.findNavController(myAccountBinding.getRoot()).navigate(R.id.action_user_profile);
                    Toast.makeText(requireActivity(), baseResponse.getMessage(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(requireActivity(), "Failed", Toast.LENGTH_SHORT).show();
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
                    break;
                case R.id.female:
                    gender = myAccountBinding.female.getText().toString();
                    break;
                case R.id.others:
                    gender = myAccountBinding.others.getText().toString();
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

        if (user.getCustomerTitle() != null) {
            String titleFromServer = user.getCustomerTitle();

            int index = 0;

            for (int i = 0; i < title.length; i++) {
                if (title[i].equals(titleFromServer)) {
                    index = i;
                    break;
                }
            }
            myAccountBinding.edtTitle.setSelection(index);
        }
        if (user.getCustomerBloodGroup() != null) {
            String bgFromServer = user.getCustomerBloodGroup();

            int index = 0;

            for (int i = 0; i < blood.length; i++) {
                if (blood[i].equals(bgFromServer)) {
                    index = i;
                    break;
                }
            }
            myAccountBinding.edtBloodGroup.setSelection(index);
        }

        if (user.getCustomerGender().equals("Male")) {
            myAccountBinding.male.setChecked(true);
            myAccountBinding.female.setChecked(false);
            myAccountBinding.others.setChecked(false);
        } else if (user.getCustomerGender().equals("Female")) {
            myAccountBinding.male.setChecked(false);
            myAccountBinding.female.setChecked(true);
            myAccountBinding.others.setChecked(false);
        } else if (user.getCustomerGender().equals("Others")) {
            myAccountBinding.male.setChecked(false);
            myAccountBinding.female.setChecked(false);
            myAccountBinding.others.setChecked(true);

        }
    }
}