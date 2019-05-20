package com.example.hanulproject.intro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.hanulproject.R;
import com.example.hanulproject.login.Login;


public class Intro extends AppCompatActivity {

    ProgressBar progressBar;
    Handler handler = new Handler(){};
    ImageView imageView3;

    int value = 0; // progressBar 값
    int add = 4; // 증가량, 방향

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro); //xml , java 소스 연결

        imageView3 = findViewById(R.id.imageView3);

        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(imageView3);
        Glide.with(this).load(R.drawable.home_img).into(gifImage);

        progressBar = findViewById(R.id.progressBar_intro);

        handler.sendEmptyMessage(0);

        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent (getApplicationContext(), Login.class);
                startActivity(intent); //다음화면으로 넘어감
                finish();
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
}
