package com.example.primegen.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.primegen.databinding.ListItemTestBinding;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {

    private Context mContext;
    private ArrayList<Test> mTestList;
    private TestClickListener mTestClickListener;
    private boolean isExpanded = true;


    public TestAdapter(Context context, ArrayList<Test> testList, TestClickListener testClickListener) {

        mContext = context;
        mTestList = testList;
        mTestClickListener = testClickListener;
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ListItemTestBinding testBinding = ListItemTestBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TestViewHolder(testBinding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
        TestAdapter.TestViewHolder mobileViewHolder = (TestAdapter.TestViewHolder) holder;
        Test test = mTestList.get(position);
        mobileViewHolder.binding.tvTestName.setText(test.getTestprofileName());
        mobileViewHolder.binding.offerPrice.setText("₹" + test.getTestprofileOfferPrice());

        mobileViewHolder.binding.actualPrice.setText("MRP ₹" + test.getTestprofileActualPrice());
        mobileViewHolder.binding.actualPrice.setPaintFlags(mobileViewHolder.binding.actualPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        if (test.getIsTestOrProfile().equals("1")) {
            mobileViewHolder.binding.type.setText("Test");
        }
        else {
            mobileViewHolder.binding.type.setText("Profile");
        }

        //mobileViewHolder.binding.included.setText(test.);
            if (test.isExpanded()) {
                mobileViewHolder.binding.testDetailCard.setVisibility(View.VISIBLE);

            } else {

                mobileViewHolder.binding.testDetailCard.setVisibility(View.GONE);
            }

    }

    @Override
    public int getItemCount() {
        return mTestList.size();
    }

    public class TestViewHolder extends RecyclerView.ViewHolder {

        ListItemTestBinding binding;

        public TestViewHolder(@NonNull ListItemTestBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;

            itemView.testCard.setOnClickListener(v -> {
                Test test = mTestList.get(getLayoutPosition());
                test.setExpanded(!test.isExpanded());

                for(int i = 0; i<mTestList.size(); i++){
                    if(i!=getLayoutPosition()){
                        Test t=mTestList.get(i);
                        t.setExpanded(false);
                    }
                }
                notifyDataSetChanged();
            });

            itemView.btnAddToCart.setOnClickListener(v ->{
                    mTestClickListener.onClick(getLayoutPosition());
                    itemView.btnAddToCart.setText("Added");

            });

            itemView.getRoot().setOnClickListener(v -> {

            });

        }

    }
}
