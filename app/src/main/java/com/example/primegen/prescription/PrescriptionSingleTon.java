package com.example.primegen.prescription;

import java.util.ArrayList;

public class PrescriptionSingleTon {


    ArrayList<Prescription> prescriptionsList = new ArrayList<>();

    private static final PrescriptionSingleTon ourInstance = new PrescriptionSingleTon();

    public static PrescriptionSingleTon getInstance() {
        return ourInstance;
    }
    private PrescriptionSingleTon() {

    }

    public ArrayList<Prescription> getPrescriptionsList() {

        return prescriptionsList;
    }

    public void setPrescriptionsList(Prescription prescriptionsList) {
        this.prescriptionsList.add(prescriptionsList);
    }
}
