package com.example.primegen.payment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.primegen.DashboardActivity;
import com.example.primegen.R;
import com.example.primegen.databinding.ActivityPaymentBinding;
import com.example.primegen.signup.User;
import com.google.gson.Gson;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {

    ActivityPaymentBinding paymentBinding;
    private String testCount;
    private double total;
    private String tests;
    private SharedPreferences mPrefs;
    private com.example.primegen.signup.User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        paymentBinding = ActivityPaymentBinding.inflate(LayoutInflater.from(this));
        setContentView(paymentBinding.getRoot());

        // testCount = getIntent().getStringExtra("test_count");
        total = getIntent().getDoubleExtra("test_total", 0);
        //tests = getIntent().getStringExtra("tests");

        mPrefs = getSharedPreferences("user_values", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        user = gson.fromJson(json, User.class);

        setBackPress();
        makePayment();
    }

    private void makePayment() {
        //String samount = "1000";
        // rounding off the amount.
        int amount = Math.round(Float.parseFloat(String.valueOf(total)) * 100);

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
            object.put("description", user.getCustomerName());
            // to set theme color
            object.put("theme.color", "#5B308A");
            // put the currency
            object.put("currency", "INR");
            // put amount
            object.put("amount", amount);
            // put mobile number
            object.put("prefill.contact", user.getCustomerPhone());
            // put email
            object.put("prefill.email", user.getCustomerEmail());
            // open razorpay to checkout activity
            checkout.open(this, object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setBackPress() {
        paymentBinding.back.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment is successful : " + s, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, PaymentSuccessActivity.class));
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Failed due to error : " + s, Toast.LENGTH_SHORT).show();
        Log.d("Payment Error", s);
    }
}