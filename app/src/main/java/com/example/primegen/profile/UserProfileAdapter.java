package com.example.primegen.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.primegen.databinding.ListItemUserprofileBinding;
import com.example.primegen.home.DashBoard;
import com.example.primegen.home.DashBoardAdapter;

import java.util.ArrayList;

public class UserProfileAdapter extends RecyclerView.Adapter<UserProfileAdapter.UserProfileViewHolder>{

    private ArrayList<User> mUserList;
    private Context mContext;

    ProfileItemClickListener mItemClickListener;
    public UserProfileAdapter(Context context, ArrayList<User> userList, ProfileItemClickListener itemClickListener){

        mContext = context;
        mUserList = userList;
        mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public UserProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemUserprofileBinding userprofileBinding = ListItemUserprofileBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new UserProfileViewHolder(userprofileBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserProfileViewHolder holder, int position) {

        UserProfileAdapter.UserProfileViewHolder mobileViewHolder = (UserProfileAdapter.UserProfileViewHolder) holder;
        User user = mUserList.get(position);
        mobileViewHolder.mUserprofileBinding.cardText.setText(user.getName());
        mobileViewHolder.mUserprofileBinding.image.setImageResource(user.getImage());

    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public class UserProfileViewHolder extends RecyclerView.ViewHolder {
        ListItemUserprofileBinding mUserprofileBinding;

        public UserProfileViewHolder(@NonNull ListItemUserprofileBinding itemView) {
            super(itemView.getRoot());

            mUserprofileBinding = itemView;

            itemView.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onClick(getLayoutPosition());
                }
            });
        }
    }
}
