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


public class HomeWindowView extends Fragment {
    private LinearLayout onWindow;
    private LinearLayout offWindow;
    private int is_window;

    public void setIs_window(int is_window) {
        this.is_window = is_window;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.home_window_view_fragment, container, false);


        onWindow = rootview.findViewById(R.id.onwindow);
        offWindow = rootview.findViewById(R.id.offwindow);

        if(is_window == 2){
            onWindow.setVisibility(View.VISIBLE);
            offWindow.setVisibility(View.GONE);
        }else if(is_window == 1){
            onWindow.setVisibility(View.GONE);
            offWindow.setVisibility(View.VISIBLE);
        }

        return rootview;
    }
}
