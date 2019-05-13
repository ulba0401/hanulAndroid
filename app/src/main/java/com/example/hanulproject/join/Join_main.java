package com.example.hanulproject.join;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.hanulproject.R;
import com.example.hanulproject.login.Login;
import com.example.hanulproject.login.Login_menu;
import com.example.hanulproject.login.Login_page;


public class Join_main extends AppCompatActivity {

    ToggleButton checkBtn;
    Button joinBtn;
    int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_main);

        checkBtn = findViewById(R.id.checkBtn);
        joinBtn = findViewById(R.id.realJoin);

        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBtn.isChecked()) {
                    check = 1;
                    checkBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.check));
                    joinBtn.setBackground(getResources().getDrawable(R.drawable.btn_on));
                    joinBtn.setTextColor(getResources().getColor(R.color.titlecolor));
                }else{
                    check = 0;
                    checkBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.noncheck));
                    joinBtn.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                    joinBtn.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check == 1){


                    /* 회원가입 처리할 부분 */


                    Intent intent = new Intent(Join_main.this, Login_page.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(Join_main.this, "내용을 확인해 주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Join_main.this, Login.class);
        startActivity(intent);
        finish();
    }
}

