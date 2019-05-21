package com.example.hanulproject.login.search;

import android.content.Context;
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
import com.example.hanulproject.join.Join_main;

public class Search_id extends Fragment {

    Search_main activity;
    Button findId;
    EditText name, email;
    TextView id1, id2, id3;


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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.login_search_id, container, false);
        findId=rootView.findViewById(R.id.findId);
        name=rootView.findViewById(R.id.finame);
        email=rootView.findViewById(R.id.fiemail);
        id1=rootView.findViewById(R.id.id1);
        id2=rootView.findViewById(R.id.id2);
        id3=rootView.findViewById(R.id.id3);


        findId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().length()==0||email.getText().toString().length()==0){
                    Toast.makeText(activity, "정보를 입력하세요.", Toast.LENGTH_SHORT).show();
                    name.requestFocus();
                    return;
                }
                FindId find=new FindId(name.getText().toString(), email.getText().toString(), activity.getApplicationContext());
                try{
                    int check=find.execute().get();
                    if(check==0){
                        Toast.makeText(activity, "입력하신 정보로 아이디를 확인할 수 없습니다.", Toast.LENGTH_SHORT).show();
                        id1.setVisibility(View.GONE);
                        id2.setVisibility(View.GONE);
                        id3.setVisibility(View.GONE);
                    }else if(check==1){
                        String id=FindId.vo.getId();
                        Log.d("NameCheck", id);
                        id2.setText(FindId.vo.getId());
                        id1.setVisibility(View.VISIBLE);
                        id2.setVisibility(View.VISIBLE);
                        id3.setVisibility(View.VISIBLE);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        return rootView;
    }


}
