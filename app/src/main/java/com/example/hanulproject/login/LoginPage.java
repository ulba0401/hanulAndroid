package com.example.hanulproject.login;

import com.example.hanulproject.MainActivity;

public class LoginPage {

    public LoginPage(){
        MainActivity.name.setText(LoginRequest.dto.getId());
        MainActivity.id.setText(LoginRequest.dto.getEmail());
    }
}
