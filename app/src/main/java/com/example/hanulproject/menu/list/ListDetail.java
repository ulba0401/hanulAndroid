package com.example.hanulproject.menu.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hanulproject.R;
import com.example.hanulproject.join.IdCheck;
import com.example.hanulproject.task.task.detail.CommunityCallDetail;
import com.example.hanulproject.vo.UserVO;

public class ListDetail extends AppCompatActivity {

    Button back, modify, delete;
    UserVO vo;
    TextView id, pw, name, addr, email;
    ImageView profile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_detail);

        profile=findViewById(R.id.profile);
        id=findViewById(R.id.uid);
        pw=findViewById(R.id.upw);
        name=findViewById(R.id.uname);
        addr=findViewById(R.id.uaddr);
        email=findViewById(R.id.uemail);

        modify=findViewById(R.id.umodify);
        delete=findViewById(R.id.udelete);
        back=findViewById(R.id.back);
        // 뒤로가기
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        vo= (UserVO) getIntent().getSerializableExtra("vo");
        //수정화면 넘어가는 곳
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
//화면 가져와서 뿌려주는 곳
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
//리셋 메소드
    void reset(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result","result");
        setResult(RESULT_OK,resultIntent);
        finish();
    }
}
