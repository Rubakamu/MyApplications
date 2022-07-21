package com.example.primegen.profile;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.primegen.R;
import com.example.primegen.databinding.FragmentPatientDetailsBinding;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;


public class PatientDetailsFragment extends Fragment implements View.OnClickListener, PaymentResultListener {

    private int mYear, mMonth, mDay;
    private int mHour;
    private int mMinute;
    private boolean checked;
    private FragmentPatientDetailsBinding mPatientDetailsBinding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mPatientDetailsBinding = FragmentPatientDetailsBinding.inflate(inflater);

        initView();
        setBackPress();
        setDateTime();
        makePayment();
        return mPatientDetailsBinding.getRoot();
    }

    private void initView() {

        mPatientDetailsBinding.btnHome.setOnClickListener(v -> {
        });
        mPatientDetailsBinding.btnWork.setOnClickListener(v -> {
        });
        mPatientDetailsBinding.btnOthers.setOnClickListener(v -> {
        });

       // mPatientDetailsBinding.paymentMode.setOnCheckedChangeListener(onCheckedChangeListener);

    }

    private void setDateTime() {

        mPatientDetailsBinding.selectMode.edtDate.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);

            //show dialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(requireActivity(), (view, year, month, dayOfMonth) ->
                    mPatientDetailsBinding.selectMode.edtDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year), mYear, mMonth, mDay);
            datePickerDialog.show();
        });

        mPatientDetailsBinding.selectMode.edtTime.setOnClickListener(v -> {

            final Calendar calendar = Calendar.getInstance();
            final TimePickerDialog timePickerDialog = new TimePickerDialog(requireActivity(),
                    (view, hourOfDay, minute) -> {
                        String AM_PM;
                        if (hourOfDay >= 0 && hourOfDay < 12) {
                            AM_PM = " AM";
                        } else {
                            AM_PM = " PM";
                        }
                        mPatientDetailsBinding.selectMode.edtTime.setText(hourOfDay + ":" + minute + "" + AM_PM);
                    }, mHour, mMinute, false);

            timePickerDialog.show();
        });

    }

    private void setBackPress() {
        mPatientDetailsBinding.back.setOnClickListener(v -> {
            getActivity().onBackPressed();

        });
    }

    @Override
    public void onClick(View v) {
        checked = ((RadioButton) v).isChecked();

        // Check which radio button was clicked
        switch(v.getId()) {
            case R.id.online:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.cash:
                if (checked)
                    // Ninjas rule
                    break;
        }
    }

    private void makePayment(){

        mPatientDetailsBinding.btnConfirm.setOnClickListener(v -> {

            String samount = "1000";

            // rounding off the amount.
            int amount = Math.round(Float.parseFloat(samount) * 100);

            // initialize Razorpay account.
            Checkout checkout = new Checkout();

            // set your id as below
            checkout.setKeyID("rzp_test_e8yMIVAfSiOtqA");

            // set image
            checkout.setImage(R.drawable.primegen_app_icon);

            // initialize json object
            JSONObject object = new JSONObject();
            try {
                // to put name
                object.put("name", "PrimeGen");

                // put description
                object.put("description", "Test payment");

                // to set theme color
                object.put("theme.color", "");

                // put the currency
                object.put("currency", "INR");

                // put amount
                object.put("amount", amount);

                // put mobile number
                object.put("prefill.contact", "9284064503");

                // put email
                object.put("prefill.email", "chaitanyamunje@gmail.com");

                // open razorpay to checkout activity
                checkout.open(getActivity(), object);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(requireActivity(), "Payment is successful : " + s, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(requireActivity(), "Payment Failed due to error : " + s, Toast.LENGTH_LONG).show();

        Log.d("Payment Error", s);

    }
}