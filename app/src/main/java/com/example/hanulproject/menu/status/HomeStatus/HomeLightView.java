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

    private boolean is_light;
    LinearLayout on;
    LinearLayout off;

    public void setIs_light(boolean is_light) {
        this.is_light = is_light;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.home_light_view_fragment, container, false);
        on = rootview.findViewById(R.id.onlight);
        off = rootview.findViewById(R.id.offlight);
        if (is_light){
            on.setVisibility(View.VISIBLE);
            off.setVisibility(View.GONE);
        }else{
            on.setVisibility(View.GONE);
            off.setVisibility(View.VISIBLE);
        }

        return rootview;
    }
}
