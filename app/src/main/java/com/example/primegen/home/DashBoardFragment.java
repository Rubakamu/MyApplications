package com.example.primegen.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.primegen.R;
import com.example.primegen.cart.CartSingleTon;
import com.example.primegen.databinding.FirstTimeUserBinding;
import com.example.primegen.databinding.FragmentDashBoardBinding;
import com.example.primegen.test.Test;
import com.example.primegen.test.TestClickListener;
import com.example.primegen.viewmodel.PrimeViewModel;

import java.util.ArrayList;
import java.util.List;

public class DashBoardFragment extends Fragment {

    private FragmentDashBoardBinding mDashBoardBinding;
    private FirstTimeUserBinding mFirstTimeBinding;
    ItemClickListener itemClickListener;
    private CartSingleTon cartSingleTon;
    private List<Test> testList;
    private final List<Test> mTestListOriginal = new ArrayList<>();
    TestSearchAdapter searchAdapter;
    TestClickListener testClickListener;
    private PrimeViewModel viewModel;
    private boolean isRegister;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mDashBoardBinding = FragmentDashBoardBinding.inflate(inflater);

        mFirstTimeBinding = FirstTimeUserBinding.inflate(inflater);
        viewModel = new ViewModelProvider(this).get(PrimeViewModel.class);


        testClickListener = position -> {

            mDashBoardBinding.edtSearch.setText(testList.get(position).getTestprofileName());

            String testSearch = mDashBoardBinding.edtSearch.getText().toString();

            Bundle bundle = new Bundle();
            bundle.putString("test_search", testSearch);
            Navigation.findNavController(mDashBoardBinding.getRoot()).navigate(R.id.action_home_to_home_detail, bundle);
        };


        itemClickListener = position -> {
            if (position == 0) {
                Navigation.findNavController(mDashBoardBinding.getRoot()).navigate(R.id.action_home_to_home_detail);
            } else if (position == 1) {
                Navigation.findNavController(mDashBoardBinding.getRoot()).navigate(R.id.action_home_to_home_detail);
            } else if (position == 2) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("isFromHeart", true);
                Navigation.findNavController(mDashBoardBinding.getRoot()).navigate(R.id.action_home_to_home_detail, bundle);
            } else if (position == 3) {
                Navigation.findNavController(mDashBoardBinding.getRoot()).navigate(R.id.action_upload_prescription);
            } else {
                callAtRuntime();
                //navigate();
            }
        };


        if (CartSingleTon.getInstance(requireActivity()).readItemCount() != 0) {
            mDashBoardBinding.tvCount.setText(String.valueOf(CartSingleTon.getInstance(requireActivity()).readItemCount()));
        }


        mDashBoardBinding.ivSearchDelete.setOnClickListener(v -> mDashBoardBinding.edtSearch.setText(""));

        getDashBoardList();
        getTestList();

        setFirstTimeUser();

        if (isAdded()) {
            isRegister = requireActivity().getIntent().getExtras().getBoolean("isRegister");
        }

            return mDashBoardBinding.getRoot();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        if (isRegister) {
            navigate();
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        isRegister=false;
    }

    @Override
    public void onPause() {
        super.onPause();
        isRegister=false;
    }

    private void setFirstTimeUser() {

        mDashBoardBinding.btnBookATest.setOnClickListener(v ->
                Navigation.findNavController(mDashBoardBinding.getRoot()).navigate(R.id.action_home_to_home_detail));
        mDashBoardBinding.btnUpdateProfile.setOnClickListener(v ->
                navigate());
        mDashBoardBinding.btnHome.setOnClickListener(v -> {
            mDashBoardBinding.firstTimeUser.setVisibility(View.GONE);
            mDashBoardBinding.clHome.setVisibility(View.VISIBLE);
        });

    }

    private void getTestList() {


        viewModel.getAllTestData().observe(requireActivity(), testResponse -> {

            if (getActivity() != null && isAdded() && isVisible()) {
                setTestList(testResponse.getTest());
            }

        });

        mDashBoardBinding.edtSearch.addTextChangedListener(new TextWatcher() {
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
                    mDashBoardBinding.testCard.setVisibility(View.VISIBLE);
                    List<Test> filteredList = new ArrayList<>();
                    for (Test test : mTestListOriginal) {
                        if (viewModel.isMatchesFound(searchText, test)) {
                            filteredList.add(test);
                        }
                    }
                    updateList(filteredList);
                } else {
                    mDashBoardBinding.testCard.setVisibility(View.GONE
                    );
                    updateList(mTestListOriginal);
                }
            }
        });


    }

    private void setTestList(List<Test> data) {
        testList = data;
        mTestListOriginal.addAll(data);

        mDashBoardBinding.rvTestSearch.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchAdapter = new TestSearchAdapter(requireActivity(), testList, testClickListener);
        mDashBoardBinding.rvTestSearch.setAdapter(searchAdapter);

//        mDashBoardBinding.edtSearch.setOnClickListener(v ->
//                mDashBoardBinding.rvTestSearch.setVisibility(View.VISIBLE));


    }

    private void updateList(List<Test> tests) {

        if (testList != null) {
            testList.clear();
            testList.addAll(tests);
            searchAdapter.notifyDataSetChanged();
        }
    }

    private void getDashBoardList() {

        ArrayList<DashBoard> dashBoardList = new ArrayList<>();

        DashBoard d1 = new DashBoard();
        d1.setCardName("All Test");
        d1.setImage(R.drawable.ic_test);

        DashBoard d2 = new DashBoard();
        d2.setCardName("Health Packages");
        d2.setImage(R.drawable.ic_health_packages);

        DashBoard d3 = new DashBoard();
        d3.setCardName("Heart");
        d3.setImage(R.drawable.ic_heart);

        DashBoard d4 = new DashBoard();
        d4.setCardName("Upload Prescription");
        d4.setImage(R.drawable.ic_upload);

        DashBoard d5 = new DashBoard();
        d5.setCardName("Book on Call");
        d5.setImage(R.drawable.ic_call);

        dashBoardList.add(d1);
        dashBoardList.add(d2);
        dashBoardList.add(d3);
        dashBoardList.add(d4);
        dashBoardList.add(d5);

        mDashBoardBinding.rvTest.setLayoutManager(new GridLayoutManager(requireActivity(), 3));
        DashBoardAdapter dashBoardAdapter = new DashBoardAdapter(requireActivity(), dashBoardList, itemClickListener);
        mDashBoardBinding.rvTest.setAdapter(dashBoardAdapter);

        mDashBoardBinding.cvCart.setOnClickListener(v ->
                Navigation.findNavController(mDashBoardBinding.getRoot()).navigate(R.id.action_add_to_cart));

        mDashBoardBinding.ivSearchDelete.setOnClickListener(v ->
                mDashBoardBinding.edtSearch.setText(""));
        mDashBoardBinding.testCard.setVisibility(View.GONE);

    }

    private void navigate() {
        Navigation.findNavController(mDashBoardBinding.getRoot()).navigate(R.id.action_my_account);
    }

    private void callAtRuntime() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);

        } else {

            String phoneNumber = "1234567890";
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(intent);

        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 1) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                callAtRuntime();

            } else {

                Toast.makeText(requireActivity(), "Oh No!!! Permission Denied. Try Again!", Toast.LENGTH_SHORT).show();

            }

        }
    }

}