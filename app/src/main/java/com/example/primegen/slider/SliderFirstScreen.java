package com.example.primegen.slider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.primegen.R;


public class SliderFirstScreen extends Fragment {

    private static final String ARG_COUNT = "param1";
    private Integer counter;
    private Integer[] FIST_TEXT = {R.string.first_slider_first_text, R.string.second_slider_first_text, R.string.third_slider_first_text};
    private Integer[] SECOND_TEXT = {R.string.first_slider_second_text, R.string.second_slider_second_text, R.string.third_slider_second_text};
    private Integer[] THIRD_TEXT = {R.string.first_slider_third_text, R.string.second_slider_third_text,R.string.third_slider_third_text};
    private int[] LAYOUT_IMAGE = {R.drawable.ic_second_slider_bg, R.drawable.ic_slider_bg, R.drawable.ic_third_slider_bg};
    private int[] IMAGE = {R.drawable.ic_third_slider, R.drawable.ic_home_collection, R.drawable.ic_dg_report};


    public SliderFirstScreen() {
    }


    public static SliderFirstScreen newInstance(Integer counter) {
        SliderFirstScreen fragment = new SliderFirstScreen();
        Bundle args = new Bundle();
        args.putInt(ARG_COUNT, counter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            counter = getArguments().getInt(ARG_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_slider_first_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppCompatTextView firstViewCounter = view.findViewById(R.id.tv_counter);
        AppCompatTextView secondViewCounter = view.findViewById(R.id.tv_counter1);
        AppCompatTextView thirdViewCounter = view.findViewById(R.id.tv_counter2);
        AppCompatImageView imageView = view.findViewById(R.id.ivSliderImage);
        ConstraintLayout background = view.findViewById(R.id.clBackground);



        firstViewCounter.setText(FIST_TEXT[counter]);
        secondViewCounter.setText(SECOND_TEXT[counter]);
        thirdViewCounter.setText(THIRD_TEXT[counter]);
        imageView.setImageResource(IMAGE[counter]);
        background.setBackgroundResource(LAYOUT_IMAGE[counter]);

    }
}