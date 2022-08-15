package com.example.primegen.base_properties;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.primegen.cart.CartSingleTon;
import com.example.primegen.test.Test;

import java.util.HashSet;
import java.util.Set;

public class BaseSingleTon {

    private static BaseSingleTon ourInstance = null;
    private static SharedPreferences mPrefs;
    private static boolean isTestAddedNewly = false;
    private final Set<Test> testList = new HashSet<>();

    private BaseSingleTon() {
    }

    public static BaseSingleTon getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new BaseSingleTon();
            mPrefs = context.getSharedPreferences("user_values", Context.MODE_PRIVATE);
        }
        return ourInstance;
    }

}
