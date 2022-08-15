package com.example.primegen.prescription;

import java.util.ArrayList;

public class PrescriptionSingleTon {


    ArrayList<Prescription> prescriptionsList = new ArrayList<>();

    private static PrescriptionSingleTon ourInstance=null;

    private PrescriptionSingleTon() {

    }

    public static PrescriptionSingleTon getInstance() {
        if(ourInstance ==null) {
            ourInstance = new PrescriptionSingleTon();
        }
        return ourInstance;
    }


    public ArrayList<Prescription> getPrescriptionsList() {

        return prescriptionsList;
    }

    public void setPrescriptionsList(Prescription prescriptionsList) {
        this.prescriptionsList.add(prescriptionsList);
    }
}
