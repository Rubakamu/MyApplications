package com.example.primegen.prescription;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.primegen.R;
import com.example.primegen.cart.CartSingleTon;
import com.example.primegen.databinding.FragmentUploadedPrescriptionBinding;

import java.util.ArrayList;

public class UploadedPrescriptionFragment extends Fragment {

    private FragmentUploadedPrescriptionBinding mUploadedPrescriptionBinding;
    ArrayList<Prescription> prescriptionsList = new ArrayList<>();
    PrescriptionItemClickListener itemClickListener;
    private PrescriptionSingleTon singleTon;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mUploadedPrescriptionBinding = FragmentUploadedPrescriptionBinding.inflate(inflater);

        itemClickListener = new PrescriptionItemClickListener() {
            @Override
            public void onClick(int position) {

                    mUploadedPrescriptionBinding.prescriptionImage.setImageURI(
                            Uri.parse(singleTon.getPrescriptionsList().get(position).getPrescription_image()));


            }

            @Override
            public void deleteClick(int position) {

            }
        };

        if (CartSingleTon.getInstance(requireActivity()).readItemCount() != 0) {
            mUploadedPrescriptionBinding.tvCount.setText(String.valueOf(CartSingleTon.getInstance(requireActivity()).readItemCount()));
        }


        setBackPress();
        initView();
        return mUploadedPrescriptionBinding.getRoot();
    }

    private void initView() {

        String image = null;
        String imageName = null;

        if (getArguments() != null) {
                image = getArguments().getString("image", "");
                 imageName = getArguments().getString("imageName", "");
            }


            Prescription prescription = new Prescription();

                prescription.setPrescription_image(image);
                prescription.setPrescription_name(imageName + ".jpg");


            prescriptionsList.add(prescription);

            singleTon = PrescriptionSingleTon.getInstance();
            singleTon.setPrescriptionsList(prescription);

            mUploadedPrescriptionBinding.rvPrescription.setLayoutManager(new LinearLayoutManager(requireActivity()));
            PrescriptionAdapter prescriptionAdapter = new PrescriptionAdapter(requireActivity(), singleTon.getPrescriptionsList(),itemClickListener);
            mUploadedPrescriptionBinding.rvPrescription.setAdapter(prescriptionAdapter);


            mUploadedPrescriptionBinding.btnNext.setOnClickListener(v ->
                    Navigation.findNavController(mUploadedPrescriptionBinding.getRoot()).navigate(R.id.action_home));

        }

    private void setBackPress() {
        mUploadedPrescriptionBinding.back.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });
    }
}