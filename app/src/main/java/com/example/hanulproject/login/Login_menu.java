package com.example.hanulproject.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.hanulproject.MainActivity;
import com.example.hanulproject.R;
import com.example.hanulproject.join.Join_main;
import com.example.hanulproject.login.search.Search_main;
import com.kakao.auth.ErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

public class Login_menu extends AppCompatActivity  {
    private LoginButton kakaoLoginButton;// 카카오 제공 Api의 로그인 버튼 뷰
    private com.facebook.login.widget.LoginButton FacebookLoginButton;// 페북 제공 Api의 로그인 버튼 뷰

    Button fake_kakao, fake_face, loginBtn;
    TextView jointxt, search_id, search_pw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_menu);

        //비밀번호 찾기 버튼
        search_pw = findViewById(R.id.search_pw);
        search_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_menu.this, Search_main.class);
                intent.putExtra("idOrPw", 2);
                startActivity(intent);
            }
        });

        //아이디 찾기 버튼
        search_id = findViewById(R.id.search_id);
        search_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_menu.this, Search_main.class);
                intent.putExtra("idOrPw", 1);
                startActivity(intent);
            }
        });

        //회원가입 버튼
        jointxt = findViewById(R.id.joinTxt);
        jointxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_menu.this, Join_main.class);
                startActivity(intent);
            }
        });

        //로그인 버튼 눌렀을때
        loginBtn = findViewById(R.id.loginMenuBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_menu.this, Login_page.class);
                startActivity(intent);
                finish();
            }
        });

        //페이스북 로그인 버튼 커스텀 적용
        FacebookLoginButton = findViewById(R.id.facebook_login);
        fake_face = findViewById(R.id.fake_face);
        fake_face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FacebookLoginButton.performClick();
            }
        });

        //카카오톡 로그인 버튼 커스텀 적용
        kakaoLoginButton = findViewById(R.id.com_kakao_login);
        fake_kakao = findViewById(R.id.fake_kakao);

        fake_kakao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kakaoLoginButton.performClick();
            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Login_menu.this, Login.class);
        startActivity(intent);
        finish();
    }
}
