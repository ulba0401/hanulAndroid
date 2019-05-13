package com.example.hanulproject.login.search;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.hanulproject.R;

public class Search_main extends AppCompatActivity{

    Search_id search_id = new Search_id();
    Search_pw search_pw = new Search_pw();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_search_main);
        Intent intent = getIntent();
        int idOrPw = intent.getIntExtra("idOrPw",3);

        if(idOrPw == 1){ onFragmentChange(1); }
        if(idOrPw == 2){ onFragmentChange(2); }

        Log.d("abc" , "" + idOrPw);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void onFragmentChange(int i) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();

        if(i==1){
            ft.addToBackStack(null);
            ft.replace(R.id.container,search_id).commit();
        }else if(i==2){
            ft.addToBackStack(null);
            ft.replace(R.id.container,search_pw).commit();
        }
    }
}
