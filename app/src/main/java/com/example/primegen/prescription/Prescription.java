package com.example.primegen.prescription;

import android.graphics.Bitmap;

public class Prescription {

    private String prescription_name;

    private String prescription_image;

    private Bitmap image;

    public String getPrescription_name() {
        return prescription_name;
    }

    public void setPrescription_name(String prescription_name) {
        this.prescription_name = prescription_name;
    }

    public String getPrescription_image() {
        return prescription_image;
    }

    public void setPrescription_image(String prescription_image) {
        this.prescription_image = prescription_image;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
