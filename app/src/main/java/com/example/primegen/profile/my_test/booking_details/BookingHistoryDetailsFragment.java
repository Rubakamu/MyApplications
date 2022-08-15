package com.example.primegen.profile.my_test.booking_details;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.primegen.databinding.FragmentBookingHistoryDetailsBinding;
import com.example.primegen.viewmodel.PrimeViewModel;

import java.util.HashSet;
import java.util.Set;

public class BookingHistoryDetailsFragment extends Fragment {

    private FragmentBookingHistoryDetailsBinding mDetailsBinding;
    private PrimeViewModel viewModel;
   // private ArrayList<BookingDetails> detailsList = new ArrayList<>();
    private Set<BookingDetails> detailsList = new HashSet<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mDetailsBinding = FragmentBookingHistoryDetailsBinding.inflate(inflater);
        viewModel = new ViewModelProvider(this).get(PrimeViewModel.class);

        String customerId = getArguments().getString("CID","");
        String uniqueId = getArguments().getString("UID","");

        viewModel.bookingDetails(customerId,uniqueId).observe(requireActivity(), new Observer<BookingDetailsResponse>() {
            @Override
            public void onChanged(BookingDetailsResponse bookingDetailsResponse) {

                mDetailsBinding.tvBookingId.setText(bookingDetailsResponse.getBookingDetails().getBookingID());

                    mDetailsBinding.tvBookingId.setText(bookingDetailsResponse.getBookingDetails().getBookingID());
                    mDetailsBinding.tvAddress.setText(bookingDetailsResponse.getBookingDetails().getBookingdetailFullAddress());
                    mDetailsBinding.tvInvoice.setText(bookingDetailsResponse.getBookingDetails().getBookingRefNo());
                    mDetailsBinding.tvBookingReqDate.setText(bookingDetailsResponse.getBookingDetails().getBookingdetailDateTime());
                    mDetailsBinding.tvBookingStatus.setText(bookingDetailsResponse.getBookingDetails().getBookingdetailStatus());
                    mDetailsBinding.tvPaymentAmount.setText(bookingDetailsResponse.getBookingDetails().getBookingdetailTotalAmount());
                    mDetailsBinding.tvPaymentMethod.setText(bookingDetailsResponse.getBookingDetails().getBookingdetailPaymentMode());
                    mDetailsBinding.tvPaymentStatus.setText(bookingDetailsResponse.getBookingDetails().getBookingdetailPaymentStatus());
                    mDetailsBinding.tvTotalAmount.setText(bookingDetailsResponse.getBookingDetails().getBookingdetailTotalAmount());

            }
        });

        return mDetailsBinding.getRoot();
    }
}