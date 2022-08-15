package com.example.primegen.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.primegen.databinding.ListItemSearchTestBinding;
import com.example.primegen.test.Test;
import com.example.primegen.test.TestAdapter;
import com.example.primegen.test.TestClickListener;

import java.util.List;

public class TestSearchAdapter extends RecyclerView.Adapter<TestSearchAdapter.TestSearchViewHolder> {

    private Context mContext;
    private List<Test> mTestList;
    private TestClickListener mTestClickListener;

    public TestSearchAdapter(Context context, List<Test> testList,TestClickListener testClickListener){
        mContext =context;
        mTestList = testList;
        mTestClickListener = testClickListener;
    }
    @NonNull
    @Override
    public TestSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemSearchTestBinding searchTestBinding = ListItemSearchTestBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TestSearchViewHolder(searchTestBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TestSearchViewHolder holder, int position) {
        TestSearchAdapter.TestSearchViewHolder mobileViewHolder = (TestSearchAdapter.TestSearchViewHolder) holder;
        Test test = mTestList.get(position);
        mobileViewHolder.mBinding.tvTestName.setText(test.getTestprofileName());

    }

    @Override
    public int getItemCount() {
        return mTestList.size();
    }

    public class TestSearchViewHolder extends RecyclerView.ViewHolder {

        ListItemSearchTestBinding mBinding;
        public TestSearchViewHolder(@NonNull ListItemSearchTestBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;

            itemView.tvTestName.setOnClickListener(v ->
                    mTestClickListener.onClick(getLayoutPosition()));
        }
    }
}
