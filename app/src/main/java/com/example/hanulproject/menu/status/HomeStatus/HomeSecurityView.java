package com.example.hanulproject.menu.status.HomeStatus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hanulproject.R;
import com.example.hanulproject.main.Second_fragment;
import com.example.hanulproject.main.statusTask.StatusSelect;
import com.example.hanulproject.vo.StatusVO;

public class HomeSecurityView extends Fragment {

    LinearLayout onsecurity;
    LinearLayout offsecurity;
    StatusVO vo = Second_fragment.statusVO;

    @Override
    public void onResume() {
        super.onResume();
        status_refresh();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.home_security_view_fragment, container, false);
        onsecurity = rootview.findViewById(R.id.onsecurity);
        offsecurity = rootview.findViewById(R.id.offsecurity);

        onsecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "문이 열려있습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        offsecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "문이 닫혀있습니다.", Toast.LENGTH_SHORT).show();
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

        onsecurity.setVisibility(View.GONE);
        offsecurity.setVisibility(View.GONE);

        if (vo.getDoor().equals("Y")){
            onsecurity.setVisibility(View.VISIBLE);
            offsecurity.setVisibility(View.GONE);
        }else{
            offsecurity.setVisibility(View.VISIBLE);
            onsecurity.setVisibility(View.GONE);
        }
    }
}
