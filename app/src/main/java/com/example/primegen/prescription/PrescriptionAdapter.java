package com.example.primegen.prescription;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.primegen.databinding.ListItemPrescriptionBinding;

import java.util.ArrayList;

public class PrescriptionAdapter extends RecyclerView.Adapter<PrescriptionAdapter.PrescriptionViewHolder> {

    private ArrayList<Prescription> mPrescriptionList;
    private Context mContext;

    PrescriptionItemClickListener mItemClickListener;

    public PrescriptionAdapter(Context context, ArrayList<Prescription> prescriptionsList, PrescriptionItemClickListener itemClickListener) {

        mContext = context;
        mPrescriptionList = prescriptionsList;

        mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public PrescriptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemPrescriptionBinding prescriptionBinding = ListItemPrescriptionBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new PrescriptionViewHolder(prescriptionBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PrescriptionViewHolder holder, int position) {
        PrescriptionViewHolder viewHolder = (PrescriptionViewHolder) holder;
        Prescription prescription = mPrescriptionList.get(position);
        viewHolder.mPrescriptionBinding.tvPreName.setText(prescription.getPrescription_name());

    }

    @Override
    public int getItemCount() {
        return mPrescriptionList.size();
    }

    public class PrescriptionViewHolder extends RecyclerView.ViewHolder {
        ListItemPrescriptionBinding mPrescriptionBinding;

        public PrescriptionViewHolder(@NonNull ListItemPrescriptionBinding itemView) {
            super(itemView.getRoot());

            mPrescriptionBinding = itemView;

            itemView.tvPreName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onClick(getLayoutPosition());
                }
            });

            itemView.deleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.deleteClick(getLayoutPosition());

                }
            });
        }
    }
}
