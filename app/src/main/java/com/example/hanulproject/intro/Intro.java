package com.example.hanulproject.intro;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.hanulproject.MainActivity;
import com.example.hanulproject.R;
import com.example.hanulproject.login.Login;
import com.example.hanulproject.login.LoginRequest;
import com.example.hanulproject.login.Login_menu;
import com.example.hanulproject.login.Login_page;
import com.google.firebase.iid.FirebaseInstanceId;

import java.security.MessageDigest;


public class Intro extends AppCompatActivity {
    private Context mContext;
    ProgressBar progressBar;
    Handler handler = new Handler(){};
    ImageView imageView3;

    String id;
    String pwd;
    String email;

    int value = 0; // progressBar 값
    int add = 4; // 증가량, 방향

    private void load() {
        // SharedPreferences 객체.get타입( 저장된 이름, 기본값 )
        // 저장된 이름이 존재하지 않을 시 기본값
        //MainActivity.saveLoginData = MainActivity.appData.getBoolean("SAVE_LOGIN_DATA", false);
        id = MainActivity.appData.getString("ID", "");
        pwd = MainActivity.appData.getString("PWD", "");
        email = MainActivity.appData.getString("Email", "");

        Log.d("SessionState","id :"+id);
        Log.d("SessionState","pw :"+pwd);
        Log.d("SessionState","email :"+email);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro); //xml , java 소스 연결

        mContext = getApplicationContext();
        getHashKey(mContext);
        MainActivity.appData = getSharedPreferences("appData", MODE_PRIVATE);
        load();


        imageView3 = findViewById(R.id.imageView3);

        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(imageView3);
        Glide.with(this).load(R.drawable.home_img).into(gifImage);

        progressBar = findViewById(R.id.progressBar_intro);

        handler.sendEmptyMessage(0);

        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                int result;
                // 로그인정보가 있으면 바로 메인액티비티로
                if(id.equals("")){
                    Intent intent = new Intent (getApplicationContext(), Login.class);
                    startActivity(intent); //다음화면으로 넘어감
                    finish();
                }else{
                    //정보는 있지만 아이디와 비밀번호가 다르면 로그인화면으로
                    LoginRequest login = new LoginRequest(id, pwd, getApplicationContext());
                    try{
                        result = login.execute().get();
                        if(result == 0){
                            Toast.makeText(Intro.this, "아이디와 비밀번호정보가 다릅니다. 다시 로그인 해주세요", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent (getApplicationContext(), Login.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Intent intent = new Intent(Intro.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        },3000); //3초 뒤에 Runner객체 실행하도록 함

        final ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar_intro);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() { // Thread 로 작업할 내용을 구현
                while(true) {
                    value = value + add;

                    handler.post(new Runnable() {
                        @Override
                        public void run() { // 화면에 변경하는 작업을 구현
                            pb.setProgress(value);
                        }
                    });

                    try {
                        Thread.sleep(100); // 시간지연
                    } catch (InterruptedException e) {    }
                } // end of while
            }
        });
        t.start(); // 쓰레드 시작
    }
    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
    @Nullable

    public static String getHashKey(Context context) {

        final String TAG = "KeyHash";

        String keyHash = null;

        try {
            PackageInfo info =
                    context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                keyHash = new String(Base64.encode(md.digest(), 0));
                Log.d(TAG, keyHash);
            }
        } catch (Exception e) {
            Log.e("name not found", e.toString());
        }if (keyHash != null) {
            return keyHash;
        } else {
            return null;
        }
    }
}
