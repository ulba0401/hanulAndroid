package com.example.hanulproject.menu.list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hanulproject.R;
import com.example.hanulproject.task.task.detail.MemberListDetailCall;
import com.example.hanulproject.vo.UserVO;

public class List_Modify extends AppCompatActivity {
    Button secess, cancle;
    UserVO vo;
    EditText id, pw, name, email, addr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_modify_activity);
        id=findViewById(R.id.lmid);
        pw=findViewById(R.id.lmpw);
        name=findViewById(R.id.lmname);
        email=findViewById(R.id.lmemail);
        addr=findViewById(R.id.lmaddr);
        secess=findViewById(R.id.usecess);
        cancle=findViewById(R.id.ucancle);

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
        MemberListDetailCall detail=new MemberListDetailCall(vo.getId());
        try{
            vo=detail.execute().get();
        }catch (Exception e){
            e.printStackTrace();
        }
        id.setText(vo.getId());
        pw.setText(vo.getPw());
        name.setText(vo.getName());
        addr.setText(vo.getAddr());
        email.setText(vo.getEmail());
    }


}
