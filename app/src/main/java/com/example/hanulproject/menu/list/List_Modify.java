package com.example.hanulproject.menu.list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hanulproject.R;
import com.example.hanulproject.login.LoginRequest;
import com.example.hanulproject.task.task.detail.MemberListDetailCall;
import com.example.hanulproject.vo.UserVO;

public class List_Modify extends AppCompatActivity {
    Button seccess, cancle;
    EditText id, name, pw, addr, email;
    UserVO vo=MemberListDetailCall.vo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_modify_activity);

        id=findViewById(R.id.lmid);
        pw=findViewById(R.id.lmpw);
        name=findViewById(R.id.lmname);
        addr=findViewById(R.id.lmaddr);
        email=findViewById(R.id.lmemail);

        seccess=findViewById(R.id.usecess);
        cancle=findViewById(R.id.ucancle);

        id.setText(vo.getId());
        pw.setText(vo.getPw());
        name.setText(vo.getName());
        addr.setText(vo.getAddr());
        email.setText(vo.getEmail());

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(List_Modify.this, ListDetail.class);
                startActivity(intent);
                finish();
            }
        });

        seccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
