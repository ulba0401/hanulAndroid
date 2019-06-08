package com.example.hanulproject.menu.status.HomeStatus;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hanulproject.R;
import com.example.hanulproject.main.Second_fragment;
import com.example.hanulproject.main.statusTask.Gas_off;
import com.example.hanulproject.main.statusTask.StatusSelect;
import com.example.hanulproject.vo.StatusVO;


public class HomeGasView extends Fragment {

    LinearLayout ongas;
    LinearLayout offgas;
    StatusVO vo = Second_fragment.statusVO;

    @Override
    public void onResume() {
        super.onResume();
        status_refresh();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.home_gas_view_fragment, container, false);
        ongas = rootview.findViewById(R.id.ongas);
        offgas = rootview.findViewById(R.id.offgas);

        ongas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gas_off gas_off = new Gas_off(Second_fragment.dialog);
                gas_off.execute();

                status_refresh();
            }
        });

        offgas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "가스를 원격으로 켤수는 없습니다.", Toast.LENGTH_SHORT).show();
            }
        });


        return rootview;
    }

    private void status_refresh(){

        StatusSelect statusSelect = new StatusSelect();
        statusSelect.setProgressDialog(Second_fragment.dialog);
        try {
            vo = statusSelect.execute().get();
        }catch (Exception e) {
            e.printStackTrace();
        }

        ongas.setVisibility(View.GONE);
        offgas.setVisibility(View.GONE);

        if (vo.getGas().equals("Y")){
            ongas.setVisibility(View.VISIBLE);
            offgas.setVisibility(View.GONE);
        }else{
            offgas.setVisibility(View.VISIBLE);
            ongas.setVisibility(View.GONE);
        }
    }
}
