package com.example.hanulproject.menu.status.HomeStatus;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.hanulproject.R;

public class HomeLightView extends Fragment {

    private int is_light;
    LinearLayout on;
    LinearLayout off;

    public void setIs_light(int is_light) {
        this.is_light = is_light;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.home_light_view_fragment, container, false);
        on = rootview.findViewById(R.id.onlight);
        off = rootview.findViewById(R.id.offlight);
        if (is_light == 1){
            on.setVisibility(View.GONE);
            off.setVisibility(View.VISIBLE);
        }else if(is_light == 2){
            on.setVisibility(View.VISIBLE);
            off.setVisibility(View.GONE);
        }

        return rootview;
    }
}
