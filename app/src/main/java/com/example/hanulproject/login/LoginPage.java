package com.example.hanulproject.login;

import com.example.hanulproject.MainActivity;

public class LoginPage {

    public LoginPage(){
        MainActivity.name.setText(LoginRequest.vo.getId());
        MainActivity.id.setText(LoginRequest.vo.getEmail());
    }
}
