package com.example.hanulproject.login.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hanulproject.R;
import com.example.hanulproject.login.Login_menu;
import com.example.hanulproject.login.Login_page;

import org.w3c.dom.Text;

public class Search_pw extends Fragment {

    Search_main activity;
    Button findPw, back;
    EditText id, name, email;
    TextView pw1, pw2, pw3;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Search_main) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.login_search_pw, container, false);

        findPw=rootView.findViewById(R.id.findPw);
        id=rootView.findViewById(R.id.fpid);
        name=rootView.findViewById(R.id.fpname);
        email=rootView.findViewById(R.id.fpemail);
        pw1=rootView.findViewById(R.id.pw1);
        pw2=rootView.findViewById(R.id.pw2);
        pw3=rootView.findViewById(R.id.pw3);
        back=rootView.findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Login_page.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        findPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id.getText().toString().length()==0||name.getText().toString().length()==0||email.getText().toString().length()==0){
                    Toast.makeText(activity, "정보를 입력하세요.", Toast.LENGTH_SHORT).show();
                    id.requestFocus();
                    return;
                }
                FindPw findpw=new FindPw(id.getText().toString(), name.getText().toString(), email.getText().toString(), activity.getApplicationContext());
                try{
                    int check=findpw.execute().get();
                    if(check==0){
                        Toast.makeText(activity, "입력하신 정보로 아이디를 확인할 수 없습니다.", Toast.LENGTH_SHORT).show();
                        pw1.setVisibility(View.GONE);
                        pw2.setVisibility(View.GONE);
                        pw3.setVisibility(View.GONE);
                    }else if(check==1){
                        String pw=FindPw.vo.getPw();
                        Log.d("NameCheck", pw);
                        pw2.setText(FindPw.vo.getPw());
                        pw1.setVisibility(View.VISIBLE);
                        pw2.setVisibility(View.VISIBLE);
                        pw3.setVisibility(View.VISIBLE);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


        return rootView;
    }
}
