package com.example.hanulproject.login.slider;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hanulproject.R;
import com.example.hanulproject.login.Login;
import com.example.hanulproject.main.First_fragment;

public class Slider_fragment1 extends Fragment {

    // Store instance variables
    private String title;
    private int page;
    Login activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Login) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    // newInstance constructor for creating fragment with arguments
    public static Slider_fragment1 newInstance(int page, String title) {
        Slider_fragment1 fragment = new Slider_fragment1();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragment.setArguments(args);
        return fragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");

    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_slider_fragment1, container, false);

        return view;
    }
}
