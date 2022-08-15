package com.example.primegen.cart;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.primegen.databinding.ListItemCartBinding;
import com.example.primegen.databinding.ListItemTestBinding;
import com.example.primegen.test.Test;
import com.example.primegen.test.TestAdapter;
import com.example.primegen.test.TestClickListener;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context mContext;
    private List<Test> mTestList;
    private CartClickListener mCartClickListener;
    private boolean isExpanded = true;

    public CartAdapter(Context context, List<Test> testList, CartClickListener cartClickListener) {

        mContext = context;
        mTestList = testList;
        mCartClickListener = cartClickListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ListItemCartBinding cartBinding = ListItemCartBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CartViewHolder(cartBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        CartViewHolder mobileViewHolder = (CartViewHolder) holder;
        Test test = mTestList.get(position);
        mobileViewHolder.mCartBinding.tvTestName.setText(test.getTestprofileName());
        mobileViewHolder.mCartBinding.price.setText("₹" + test.getTestprofileOfferPrice());



    }

    @Override
    public int getItemCount() {

        return mTestList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        ListItemCartBinding mCartBinding;
        public CartViewHolder(@NonNull ListItemCartBinding itemView) {
            super(itemView.getRoot());

            mCartBinding = itemView;

            itemView.deleteImage.setOnClickListener(v -> mCartClickListener.onClick(getLayoutPosition()));
        }
    }
}