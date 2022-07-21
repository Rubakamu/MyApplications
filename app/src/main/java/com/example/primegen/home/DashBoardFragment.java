package com.example.primegen.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.primegen.R;
import com.example.primegen.databinding.FragmentDashBoardBinding;

import java.util.ArrayList;

public class DashBoardFragment extends Fragment {

    private FragmentDashBoardBinding mDashBoardBinding;
    ItemClickListener itemClickListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mDashBoardBinding = FragmentDashBoardBinding.inflate(inflater);

        itemClickListener = position -> {

            if (position == 0) {
                Navigation.findNavController(mDashBoardBinding.getRoot()).navigate(R.id.action_home_to_home_detail);
            } else if (position == 1) {

                Navigation.findNavController(mDashBoardBinding.getRoot()).navigate(R.id.action_home_to_home_detail);
            } else if (position == 2) {
                Navigation.findNavController(mDashBoardBinding.getRoot()).navigate(R.id.action_upload_prescription);

            } else {
                callAtRuntime();
            }

        };

        getDashBoardList();
        return mDashBoardBinding.getRoot();

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
        d3.setCardName("Upload Prescription");
        d3.setImage(R.drawable.ic_upload);

        DashBoard d4 = new DashBoard();
        d4.setCardName("Book on Call");
        d4.setImage(R.drawable.ic_call);

        dashBoardList.add(d1);
        dashBoardList.add(d2);
        dashBoardList.add(d3);
        dashBoardList.add(d4);

        mDashBoardBinding.rvTest.setLayoutManager(new GridLayoutManager(requireActivity(), 2));
        DashBoardAdapter dashBoardAdapter = new DashBoardAdapter(requireActivity(), dashBoardList, itemClickListener);
        mDashBoardBinding.rvTest.setAdapter(dashBoardAdapter);

        mDashBoardBinding.cvCart.setOnClickListener(v ->
                Navigation.findNavController(mDashBoardBinding.getRoot()).navigate(R.id.action_add_to_cart));

        mDashBoardBinding.ivSearchDelete.setOnClickListener(v ->
                mDashBoardBinding.edtSearch.setText(""));

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