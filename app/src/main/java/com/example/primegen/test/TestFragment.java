package com.example.primegen.test;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.primegen.R;
import com.example.primegen.base_properties.Branch;
import com.example.primegen.cart.CartSingleTon;
import com.example.primegen.databinding.FragmentTestBinding;
import com.example.primegen.signup.User;
import com.example.primegen.viewmodel.PrimeViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class TestFragment extends Fragment {

    private FragmentTestBinding mTestBinding;
    private PrimeViewModel viewModel;
    private TestClickListener testClickListener;
    private SharedPreferences mPrefs;
    private User user;
    private Test test;
    private CartSingleTon cartSingleTon;
    private List<Test> testList;
    private final List<Test> mTestListOriginal = new ArrayList<>();
    private TestAdapter testAdapter;
    private List<Branch> branchList;
    private String locationId;
    private AppCompatButton btnLocation;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mTestBinding = FragmentTestBinding.inflate(inflater);

        mPrefs = requireActivity().getSharedPreferences("test_value", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("cart", "");
        cartSingleTon = gson.fromJson(json, CartSingleTon.class);
        viewModel = new ViewModelProvider(this).get(PrimeViewModel.class);


        testClickListener = position -> {
            CartSingleTon.getInstance(requireActivity()).
                    setTestList(testList.get(position));
            CartSingleTon.getInstance(requireActivity()).addedNewTest(true);
            if (CartSingleTon.getInstance(requireActivity()).readItemCount() != 0) {
                mTestBinding.tvCount.setText(String.valueOf(CartSingleTon.getInstance(requireActivity()).readItemCount()));
            }

        };

        getTestList();
        setBackPress();
        setLocation();

        btnLocation = mTestBinding.getRoot().findViewById(R.id.btnLocation);
        return mTestBinding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getTestList() {

        if (CartSingleTon.getInstance(requireActivity()).readItemCount() != 0) {
            mTestBinding.tvCount.setText(String.valueOf(CartSingleTon.getInstance(requireActivity()).readItemCount()));
        }
        viewModel.getAllTestData().observe(requireActivity(), testResponse -> {

            if (getActivity() != null && isAdded() && isVisible()) {
                setTestList(testResponse.getTest());
            }

        });


        mTestBinding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchText = s.toString().toLowerCase();
                if (!TextUtils.isEmpty(searchText)) {
                    List<Test> filteredList = new ArrayList<>();
                    for (Test test : mTestListOriginal) {
                        if (viewModel.isMatchesFound(searchText, test)) {
                            filteredList.add(test);
                        }
                    }
                    updateList(filteredList);
                } else {
                    updateList(mTestListOriginal);
                }
            }
        });

        mTestBinding.cvCart.setOnClickListener(v -> {

            SharedPreferences.Editor prefsEditor = mPrefs.edit();
            Gson gson1 = new Gson();
            String json1 = gson1.toJson(user);
            prefsEditor.putString("MyObject", json1);
            prefsEditor.commit();

            String location = mTestBinding.tvLocationName.getText().toString();

            Bundle bundle = new Bundle();
            bundle.putString("location", location);
            bundle.putString("locationId", locationId);
            Navigation.findNavController(mTestBinding.getRoot()).navigate(R.id.action_add_to_cart, bundle);
        });

        mTestBinding.ivSearchDelete.setOnClickListener(v ->
                mTestBinding.edtSearch.setText(""));
    }

    private void setTestList(List<Test> data) {
        testList = data;
        mTestListOriginal.addAll(data);

        mTestBinding.rvTestList.setLayoutManager(new LinearLayoutManager(getActivity()));
        testAdapter = new TestAdapter(requireActivity(), testList, testClickListener);
        mTestBinding.rvTestList.setAdapter(testAdapter);

        if (getArguments() != null) {

            if (getArguments().getBoolean("isFromHeart")) {
                boolean isFromHeart = getArguments().getBoolean("isFromHeart");
                if (isFromHeart) {
                    mTestBinding.edtSearch.setText("3");
                    mTestBinding.edtSearch.setFocusable(false);
                    mTestBinding.edtSearch.setCursorVisible(false);
                }
            } else if (getArguments().getString("test_search") != null) {
                String testName = getArguments().getString("test_search");
                mTestBinding.edtSearch.setText(testName);

            }

        }

    }

    private void updateList(List<Test> tests) {
        testList.clear();
        testList.addAll(tests);
        testAdapter.notifyDataSetChanged();
    }

    private void setBackPress() {
        mTestBinding.back.setOnClickListener(v -> {
            Navigation.findNavController(mTestBinding.getRoot()).navigate(R.id.action_home);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTestBinding = null;
    }

    public void setLocation() {

        viewModel.getAllBrachData().observe(requireActivity(), branchResponse -> {

            branchList = new ArrayList<>();
            if (getActivity() != null && isAdded() && isVisible()) {
                if(branchResponse.getBranch() !=null) {
                    branchList.addAll(branchResponse.getBranch());
                }
            }

            btnLocation.setOnClickListener(v -> {

                AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
                builder.setTitle("Select Location");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(requireActivity(), android.R.layout.simple_list_item_1);
                arrayAdapter.clear();
                if (branchList != null) {
                    for (int i = 0; i < branchList.size(); i++) {
                        Branch branches = branchList.get(i);
                        arrayAdapter.add(branches.getBranchID() + ". " + branches.getBranchName());
                    }
                }
                builder.setAdapter(arrayAdapter, (dialog, which) -> {
                    mTestBinding.tvLocationName.setText(branchList.get(which).getBranchName());
                    locationId = branchList.get(which).getBranchID();
                });
                builder.setPositiveButton("Cancel", (dialog, which) -> dialog.dismiss());
                AlertDialog alert = builder.create();
                alert.show();
            });
        });
    }
}