package com.example.primegen.cart;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.primegen.test.Test;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CartSingleTon {

    private static CartSingleTon ourInstance = null;
    private static SharedPreferences mPrefs;
    private static boolean isTestAddedNewly = false;
    private final Set<Test> testList = new HashSet<>();

    private CartSingleTon() {
    }

    public static CartSingleTon getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new CartSingleTon();
            mPrefs = context.getSharedPreferences("test_value", Context.MODE_PRIVATE);
        }
        return ourInstance;
    }

    public ArrayList<Test> getTestList() {

        return new ArrayList<>(testList);
    }

    public void setTestList(Test testList) {
        this.testList.add(testList);
    }

    public void remove(String id) {
        ArrayList<Test> list = new ArrayList<>(testList);
        for (int i = 0; i < list.size(); i++) {
            Test test = list.get(i);
            if (id.equals(test.getTestprofileID())) {
                list.remove(test);
            }
        }
        testList.clear();
        testList.addAll(list);
    }

    public void addedNewTest(boolean added) {
        isTestAddedNewly = added;
    }

    public boolean isTestAddedNewly() {
        return isTestAddedNewly;
    }

    public void updateCart(@NonNull List<Test> list) {
        testList.clear();
        testList.addAll(list);
    }

    public void save() {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(ourInstance);
        prefsEditor.putString("cart", json);
        Log.d("Test File", json);
        prefsEditor.commit();
    }

    public ArrayList<Test> read() {
        if (isTestAddedNewly()) {
            isTestAddedNewly = false;
            return new ArrayList<>(testList);
        }
        Gson gson = new Gson();
        String json = mPrefs.getString("cart", "");
        CartSingleTon singleTon = gson.fromJson(json, CartSingleTon.class);
        if (singleTon != null && singleTon.getTestList() != null) {
            updateCart(singleTon.getTestList());
        }
        return new ArrayList<>(testList);
    }

    public void saveItemCount() {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putInt("count", testList.size());
        Log.d("Test File", String.valueOf(testList.size()));
        editor.commit();
    }

    public int readItemCount() {

        //int itemCount = mPrefs.getInt("count", 0);
        int itemCount = testList.size();
        return itemCount;

    }
//    public int total() {
//
//        int total = 0;
//        for (int i = 0; i<testList.size(); i++)
//        {
//            total += Double.parseDouble(testList.);
//
//        }
//        return total;
//    }
}
