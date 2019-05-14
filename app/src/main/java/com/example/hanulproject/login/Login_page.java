package com.example.hanulproject.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hanulproject.MainActivity;
import com.example.hanulproject.R;
import com.example.hanulproject.join.Join_main;
import com.example.hanulproject.login.search.Search_main;

public class Login_page extends AppCompatActivity {

    Button loginMenuBtn;
    TextView jointxt, search_id, search_pw;

    private EditText id, pw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        id=findViewById(R.id.input_id);
        pw=findViewById(R.id.input_pw);
        //비밀번호 찾기 버튼
        search_pw = findViewById(R.id.search_pw);
        search_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_page.this, Search_main.class);
                intent.putExtra("idOrPw", 2);
                startActivity(intent);
            }
        });

        //아이디 찾기 버튼
        search_id = findViewById(R.id.search_id);
        search_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_page.this, Search_main.class);
                intent.putExtra("idOrPw", 1);
                startActivity(intent);
            }
        });

        //회원가입 버튼
        jointxt = findViewById(R.id.joinTxt);
        jointxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_page.this, Join_main.class);
                startActivity(intent);
            }
        });

        //로그인 버튼
        loginMenuBtn = findViewById(R.id.loginMenuBtn);
        loginMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //로그인처리 할 부분
                if(id.getText().toString().length()==0 || pw.getText().toString().length()==0){
                    Toast.makeText(Login_page.this, "아이디와 비밀번호는 필수사항입니다.", Toast.LENGTH_SHORT).show();
                    if(id.getText().toString().length()==0 ){
                        id.requestFocus();
                    }else{pw.requestFocus();}
                    return;
                }//로그인 비밀번호 아이디 공백시 다시 입력받게
                LoginRequest request = new LoginRequest(id.getText().toString(), pw.getText().toString(), getApplicationContext());
                try {
                    int check = request.execute().get();
                    if(check == 0){
                        Toast.makeText(Login_page.this, "아이디와 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                    }else{
                        String id = LoginRequest.vo.getId();
                        String name = LoginRequest.vo.getName();
                        Log.d("idCheck",id);
                        Log.d("nameCheck",name);
                        Intent intent = new Intent(Login_page.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
