package com.example.hanulproject.main;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.hanulproject.R;
import com.example.hanulproject.menu.status.HomeStatus.HomeBoilerView;
import com.example.hanulproject.menu.status.HomeStatus.HomeGasView;
import com.example.hanulproject.menu.status.HomeStatus.HomeLightView;
import com.example.hanulproject.menu.status.HomeStatus.HomeSecurityView;
import com.example.hanulproject.menu.status.HomeStatus.HomeWaterView;
import com.example.hanulproject.menu.status.HomeStatus.HomeWindowView;

public class Second_fragment extends Fragment {
    // Store instance variables
    private String title;
    private int page;
    LinearLayout water, light, secom, gas, boiler, window, status;


    // newInstance constructor for creating fragment with arguments
    public static Second_fragment newInstance(int page, String title) {
        Second_fragment fragment = new Second_fragment();
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
        FragmentManager manager=(getActivity().getSupportFragmentManager());
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.main_fragment2, container, false);
        water = rootview.findViewById(R.id.status_water);
        light = rootview.findViewById(R.id.status_light);
        secom = rootview.findViewById(R.id.status_secom);
        gas = rootview.findViewById(R.id.status_gas);
        boiler = rootview.findViewById(R.id.status_boiler);
        window = rootview.findViewById(R.id.status_window);
        status=rootview.findViewById(R.id.status);

        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.status, new HomeWaterView()).commit();
            }
        });
        light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.status, new HomeLightView()).commit();
            }
        });
        secom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.status, new HomeSecurityView()).commit();
            }
        });
        gas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.status, new HomeGasView()).commit();
            }
        });
        boiler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.status, new HomeBoilerView()).commit();
            }
        });
        window.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.status, new HomeWindowView()).commit();
            }
        });



        return rootview;
    }
}
