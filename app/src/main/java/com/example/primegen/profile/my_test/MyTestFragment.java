package com.example.primegen.profile.my_test;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.primegen.R;
import com.example.primegen.cart.CartSingleTon;
import com.example.primegen.databinding.FragmentMyTestBinding;
import com.example.primegen.viewmodel.PrimeViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MyTestFragment extends Fragment {

    private FragmentMyTestBinding myTestBinding;
    private SharedPreferences mPrefs;
    private com.example.primegen.signup.User user;
    private PrimeViewModel viewModel;
    private List<BookingHistory> bookingList = new ArrayList<>();
    private BookingHistoryClickListener clickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myTestBinding = FragmentMyTestBinding.inflate(inflater);

        viewModel = new ViewModelProvider(this).get(PrimeViewModel.class);

        mPrefs = requireActivity().getSharedPreferences("user_values", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        user = gson.fromJson(json, com.example.primegen.signup.User.class);

        clickListener = new BookingHistoryClickListener() {
            @Override
            public void onClick(int position) {

                Bundle bundle = new Bundle();
                bundle.putString("CID", user.getCustomerID());
                bundle.putString("UID", bookingList.get(position).getBookingUniqueID());

                Navigation.findNavController(myTestBinding.getRoot()).navigate(R.id.action_booking_details,bundle);
            }
        };

        if (CartSingleTon.getInstance(requireActivity()).readItemCount() != 0) {
            myTestBinding.tvCount.setText(String.valueOf(CartSingleTon.getInstance(requireActivity()).readItemCount()));
        }

        initView();
        setBackPress();
        return myTestBinding.getRoot();
    }

    private void initView() {


        myTestBinding.cvCart.setOnClickListener(v ->
                Navigation.findNavController(myTestBinding.getRoot()).navigate(R.id.action_add_to_cart));


        viewModel.bookingHistory(user.getCustomerID()).observe(requireActivity(), new Observer<BookingHistoryResponse>() {
            @Override
            public void onChanged(BookingHistoryResponse bookingHistoryResponse) {
                if (getActivity() != null && isAdded()) {
                    if (bookingHistoryResponse.getBookingHistory() != null) {

                        bookingList.addAll(bookingHistoryResponse.getBookingHistory());
                        myTestBinding.rvMyTestList.setLayoutManager(new LinearLayoutManager(getActivity()));
                        BookingHistoryAdapter historyAdapter = new BookingHistoryAdapter(requireActivity(), bookingList, clickListener);
                        myTestBinding.rvMyTestList.setAdapter(historyAdapter);
                    }
                }
            }
        });

    }

    private void setBackPress() {
        myTestBinding.back.setOnClickListener(v -> {
            getActivity().onBackPressed();

        });
    }
}