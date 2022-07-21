package com.example.primegen.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.primegen.R;
import com.example.primegen.databinding.ActivityPaymentBinding;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener, PaymentResultListener {

    private int mYear, mMonth, mDay;
    private int mHour;
    private int mMinute;
    private boolean checked;
    ActivityPaymentBinding paymentBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        paymentBinding = ActivityPaymentBinding.inflate(LayoutInflater.from(this));
        setContentView(paymentBinding.getRoot());

        setBackPress();
        setDateTime();
        makePayment();
    }
    private void initView() {

        paymentBinding.btnHome.setOnClickListener(v -> {
        });
        paymentBinding.btnWork.setOnClickListener(v -> {
        });
        paymentBinding.btnOthers.setOnClickListener(v -> {
        });

        // mPatientDetailsBinding.paymentMode.setOnCheckedChangeListener(onCheckedChangeListener);

    }

    private void setDateTime() {
        paymentBinding.selectMode.edtDate.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);

            //show dialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) ->
                    paymentBinding.selectMode.edtDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year), mYear, mMonth, mDay);
            datePickerDialog.show();
        });


        paymentBinding.selectMode.edtTime.setOnClickListener(v -> {

            final Calendar calendar = Calendar.getInstance();


            final TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    (view, hourOfDay, minute) -> {
                        String AM_PM;
                        if (hourOfDay >= 0 && hourOfDay < 12) {
                            AM_PM = " AM";
                        } else {
                            AM_PM = " PM";
                        }
                        paymentBinding.selectMode.edtTime.setText(hourOfDay + ":" + minute + "" + AM_PM);
                    }, mHour, mMinute, false);

            timePickerDialog.show();
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

        paymentBinding.btnConfirm.setOnClickListener(v -> {

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
                checkout.open(this, object);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        });
    }

    private void setBackPress() {
        paymentBinding.back.setOnClickListener(v -> {
            onBackPressed();

        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment is successful : " + s, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,PaymentSuccessActivity.class));

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Failed due to error : " + s, Toast.LENGTH_SHORT).show();

        Log.d("Payment Error", s);

    }
}