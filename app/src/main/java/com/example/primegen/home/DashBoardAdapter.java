package com.example.primegen.home;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.example.primegen.R;
import com.example.primegen.databinding.ListItemDashboardBinding;

import java.util.ArrayList;

public class DashBoardAdapter extends RecyclerView.Adapter<DashBoardAdapter.DashBoardViewHolder> {

    private ArrayList<DashBoard> mDashList;
    private Context mContext;
    ItemClickListener mItemClickListener;
    int selectedPosition = -1;

    public DashBoardAdapter(Context context, ArrayList<DashBoard> list, ItemClickListener itemClickListener) {
        mDashList = list;
        mItemClickListener = itemClickListener;
        mContext = context;
    }


    @NonNull
    @Override
    public DashBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemDashboardBinding dashboardBinding = ListItemDashboardBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new DashBoardViewHolder(dashboardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DashBoardViewHolder holder, int position) {
        DashBoardViewHolder mobileViewHolder = (DashBoardViewHolder) holder;
        DashBoard mobile = mDashList.get(position);
        mobileViewHolder.binding.cardText.setText(mobile.getCardName());
        mobileViewHolder.binding.image.setImageResource(mobile.getImage());


        if(position == 1){
            //mobileViewHolder.binding.cardView.setCardBackgroundColor(R.color.primary_layout_bg);
            mobileViewHolder.binding.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.primary_layout_bg));

        }

    }

    @Override
    public int getItemCount() {
        return mDashList.size();
    }

    public class DashBoardViewHolder extends RecyclerView.ViewHolder {
        ListItemDashboardBinding binding;

        public DashBoardViewHolder(@NonNull ListItemDashboardBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;

            itemView.getRoot().setOnClickListener(v ->
                    mItemClickListener.onClick(getLayoutPosition()));
        }

    }

}
