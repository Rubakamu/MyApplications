package com.example.primegen.base_properties;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.primegen.viewmodel.PrimeViewModel;

import java.util.ArrayList;


public class SingleChoiceDialogFragment extends DialogFragment {

    int position = 0; //default selected position
    private PrimeViewModel viewModel;

    public interface SingleChoiceListener {
        void onPositiveButtonClicked(String[] list, int position);

        void onNegativeButtonClicked();
    }

    SingleChoiceListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (SingleChoiceListener) context;
        } catch (Exception e) {
            throw new ClassCastException(getActivity().toString() + " SingleChoiceListener must implemented");
        }
    }

//    @NonNull
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        viewModel.getAllBrachData().observe(requireActivity(), branchResponse -> {
//
//                    branchList = new ArrayList<>();
//                    if (getActivity() != null && isAdded() && isVisible()) {
//                        if (branchResponse.getBranch() != null) {
//                            branchList.addAll(branchResponse.getBranch());
//                        }
//                    }
//                });
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//
//        //final String[] list = getActivity().getResources().getStringArray(R.array.choice_items);
//
//        builder.setTitle("Select your Choice")
//                .setSingleChoiceItems(list, position, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        position = i;
//                    }
//                })
//                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        mListener.onPositiveButtonClicked(list, position);
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        mListener.onNegativeButtonClicked();
//                    }
//                });
//
//        return builder.create();
//    }
}
