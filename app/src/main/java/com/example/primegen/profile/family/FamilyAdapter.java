package com.example.primegen.profile.family;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.primegen.databinding.ListItemFamilyBinding;

import java.util.ArrayList;

public class FamilyAdapter extends RecyclerView.Adapter<FamilyAdapter.FamilyViewHolder> {

    private Context mContext;
    private ArrayList<Family> mFamilyList;
    private FamilyItemClickListener mItemClickListener;


    public FamilyAdapter(Context context, ArrayList<Family> familyList,FamilyItemClickListener itemClickListener) {

        mContext = context;
        mFamilyList = familyList;
        mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public FamilyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemFamilyBinding familyBinding = ListItemFamilyBinding.inflate(LayoutInflater.from(parent.getContext()));

        return new FamilyViewHolder(familyBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FamilyViewHolder holder, int position) {
        FamilyViewHolder viewHolder = (FamilyViewHolder) holder;
        Family family = mFamilyList.get(position);
        viewHolder.mFamilyBinding.userRelationName.setText(family.getRelativeName());
        viewHolder.mFamilyBinding.userRelationship.setText(family.getRelationshipName());


    }

    @Override
    public int getItemCount() {
        return mFamilyList.size();
    }

    public class FamilyViewHolder extends RecyclerView.ViewHolder {

        ListItemFamilyBinding mFamilyBinding;

        public FamilyViewHolder(@NonNull ListItemFamilyBinding itemView) {
            super(itemView.getRoot());

            mFamilyBinding = itemView;
            itemView.getRoot().setOnClickListener(v ->
                    mItemClickListener.onClick(getLayoutPosition()));
        }
    }
}
