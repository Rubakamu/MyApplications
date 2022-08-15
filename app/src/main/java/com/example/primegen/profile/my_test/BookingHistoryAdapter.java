package com.example.primegen.profile.my_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.primegen.databinding.ListMyTestBinding;
import com.example.primegen.profile.ProfileItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class BookingHistoryAdapter extends RecyclerView.Adapter<BookingHistoryAdapter.BookingHistoryViewHolder> {

    private Context mContext;
    private List<BookingHistory> mBookingList;
    BookingHistoryClickListener mClickListener;

    public BookingHistoryAdapter(Context context, List<BookingHistory> bookingList, BookingHistoryClickListener clickListener) {

        mContext = context;
        mBookingList = bookingList;
        mClickListener = clickListener;
    }

    @NonNull
    @Override
    public BookingHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListMyTestBinding myTestBinding = ListMyTestBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BookingHistoryViewHolder(myTestBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingHistoryViewHolder holder, int position) {

        BookingHistoryViewHolder viewHolder = (BookingHistoryViewHolder) holder;
        BookingHistory bookingHistory = mBookingList.get(position);
        viewHolder.mMyTestBinding.tvTestName.setText(bookingHistory.getBookingRefNo());
        viewHolder.mMyTestBinding.tvDate.setText(bookingHistory.getBookingDateTime());
        viewHolder.mMyTestBinding.price.setText(String.format("₹ %s", bookingHistory.getBookingTotalAmount()));
        viewHolder.mMyTestBinding.tvStatus.setText(bookingHistory.getBookingPaymentStatus());
        viewHolder.mMyTestBinding.tvPayment.setText(bookingHistory.getBookingActive());

    }

    @Override
    public int getItemCount() {
        return mBookingList.size();
    }

    public class BookingHistoryViewHolder extends RecyclerView.ViewHolder {

        ListMyTestBinding mMyTestBinding;
        public BookingHistoryViewHolder(@NonNull ListMyTestBinding itemView) {
            super(itemView.getRoot());
            mMyTestBinding = itemView;

            mMyTestBinding.tvTestName.setOnClickListener(v ->
                    mClickListener.onClick(getLayoutPosition()));
        }
    }
}
