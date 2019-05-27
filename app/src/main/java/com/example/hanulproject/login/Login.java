package com.example.hanulproject.login;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.hanulproject.R;
import com.example.hanulproject.join.Join_main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.kakao.util.helper.Utility.getPackageInfo;

public class Login extends AppCompatActivity {

    Button loginBtn, joinBtn;
    private static final String TAG = "";
    FragmentPagerAdapter adapterViewPager;
    ImageView main_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        main_img = findViewById(R.id.imageView3);

        loginBtn = findViewById(R.id.loginBtn);
        joinBtn = findViewById(R.id.joinBtn);
        main_img = findViewById(R.id.main_img);

        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(main_img);
        Glide.with(this).load(R.drawable.iotmain).into(gifImage);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Login_menu.class);
                startActivity(intent);
                finish();
            }
        });
        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Join_main.class);
                startActivity(intent);
                finish();
            }
        });

    }



}


