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
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.kakao.auth.ErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import org.json.JSONObject;

import java.util.Arrays;

public class Login_menu extends AppCompatActivity  {
    SessionCallback callback;

    private CallbackManager callbackManager;

    private LoginButton kakaoLoginButton;// 카카오 제공 Api의 로그인 버튼 뷰
    private com.facebook.login.widget.LoginButton FacebookLoginButton;// 페북 제공 Api의 로그인 버튼 뷰

    Button fake_kakao, fake_face, loginBtn;
    TextView jointxt, search_id, search_pw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());//facebook sdk 초기화
        setContentView(R.layout.login_menu);
        //페이스북 로그인
        callbackManager = CallbackManager.Factory.create();

        //페이스북 로그인 버튼 커스텀 적용
        FacebookLoginButton = findViewById(R.id.facebook_login);
        fake_face = findViewById(R.id.fake_face);
        fake_face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FacebookLoginButton.performClick();
            }
        });
        FacebookLoginButton.setReadPermissions(Arrays.asList("public_profile", "email"));

        FacebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest graphRequest
                        = GraphRequest.newMeRequest(
                        loginResult.getAccessToken()
                        , new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("result",object.toString());

                                Intent intent = new Intent(Login_menu.this, MainActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Log.e("LoginErr",error.toString());
            }
        });





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

        /**카카오톡 로그아웃 요청**/
        //한번 로그인이 성공하면 세션 정보가 남아있어서 로그인창이 뜨지 않고 바로 onSuccess()메서드를 호출합니다.
        //테스트 하시기 편하라고 매번 로그아웃 요청을 수행하도록 코드를 넣었습니다 ^^
        UserManagement.getInstance().requestMe(new MeResponseCallback(){
            @Override
            public void onSuccess(UserProfile result) {

            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {

            }

            @Override
            public void onNotSignedUp() {

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


        //카카오톡 로그인 버튼 커스텀 적용
        kakaoLoginButton = findViewById(R.id.com_kakao_login);
        fake_kakao = findViewById(R.id.fake_kakao);

        fake_kakao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kakaoLoginButton.performClick();
            }
        });
        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);

    }

    //카카오 로그인
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //간편로그인시 호출 ,없으면 간편로그인시 로그인 성공화면으로 넘어가지 않음
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
        //페이스북 콜백
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }





    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            UserManagement.getInstance().requestMe(new MeResponseCallback(){
                public void onSessionClosed(ErrorResult errorResult) {
                }
                @Override
                public void onNotSignedUp() {
                }
                @Override
                public void onSuccess(UserProfile userProfile) {
                    Log.e("UserProfile", userProfile.toString());
                    Log.d("kakao", userProfile.getEmail());
                    Log.d("kakao", userProfile.getNickname());

                    LoginRequest.vo.setId(userProfile.getEmail());
                    LoginRequest.vo.setName(userProfile.getNickname());
                    Intent intent = new Intent(Login_menu.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            });
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Logger.e(exception);
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Login_menu.this, Login.class);
        startActivity(intent);
        finish();
    }
}
