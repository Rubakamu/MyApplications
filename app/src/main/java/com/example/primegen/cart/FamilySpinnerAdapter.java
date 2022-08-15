package com.example.primegen.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.primegen.R;
import com.example.primegen.profile.family.Family;

import java.util.ArrayList;

public class FamilySpinnerAdapter extends ArrayAdapter<Family> {

    public FamilySpinnerAdapter(@NonNull Context context, int resource, ArrayList<Family> families) {
        super(context, resource,families);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable
            View convertView, @NonNull ViewGroup parent)
    {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable
            View convertView, @NonNull ViewGroup parent)
    {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView,
                          ViewGroup parent)
    {
        // It is used to set our custom view.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_spinner, parent, false);
        }

        TextView textViewName = convertView.findViewById(R.id.tvRelativeName);
        Family currentItem = getItem(position);

        if (currentItem != null) {
            textViewName.setText(currentItem.getRelativeName());
            //textViewName.setText(currentItem.getRelativeAltMobile());
        }
        return convertView;
    }
}
