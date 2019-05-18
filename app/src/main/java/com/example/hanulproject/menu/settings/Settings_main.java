package com.example.hanulproject.menu.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hanulproject.R;
import com.example.hanulproject.login.LoginRequest;
import com.example.hanulproject.login.Member_modify;
import com.example.hanulproject.menu.Menu_main;

public class Settings_main extends Fragment {
    Menu_main activity;
    TextView memberModify;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Menu_main) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.settings_main, container, false);
        memberModify = rootView.findViewById(R.id.memberModify);

        if(LoginRequest.vo.isLogintype()){
            memberModify.setVisibility(View.VISIBLE);
        }else{
            memberModify.setVisibility(View.GONE);
        }

        memberModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Member_modify.class);
                startActivity(intent);
            }
        });



        return rootView;
    }
}
