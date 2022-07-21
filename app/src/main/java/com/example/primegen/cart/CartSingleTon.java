package com.example.primegen.cart;

import com.example.primegen.test.Test;

import java.util.ArrayList;

public class CartSingleTon {


    ArrayList<Test> testList = new ArrayList<>();

    private static final CartSingleTon ourInstance = new CartSingleTon();

    public static CartSingleTon getInstance() {

        return ourInstance;
    }

    private CartSingleTon() {

    }

    public ArrayList<Test> getTestList() {
        return testList;
    }

    public void setTestList(Test testList) {

        this.testList.add(testList);
    }

    public void remove(String id) {
        for (int i = 0; i < testList.size(); i++) {
            Test test = testList.get(i);
            if(id == test.getTestprofileID()){
                 testList.remove(i);
            }

        }
    }
}
