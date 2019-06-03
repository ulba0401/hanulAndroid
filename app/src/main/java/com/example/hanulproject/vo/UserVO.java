package com.example.hanulproject.vo;

import java.io.Serializable;

public class UserVO implements Serializable {
    String id, pw, name, addr, phone, email, profile, admin, isdel, profileName, deviceToken, issns;
    private String result;
    boolean logintype;

    public UserVO () {}

    public UserVO(String id, String pw, String name, String addr, String phone, String email, String profile, String admin, String profileName) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.addr = addr;
        this.phone = phone;
        this.email = email;
        this.profile = profile;
        this.admin = admin;
        this.profileName = profileName;
    }

    public UserVO(String id,String pw,String name,String email,String addr,String profile){
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.email = email;
        this.addr = addr;
        this.profile = profile;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public boolean isLogintype() {
        return logintype;
    }

    public void setLogintype(boolean logintype) {
        this.logintype = logintype;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    //로그인할때 이전의 기록을 삭제 시켜주는 메소드
    public void setClear(){
        this.id = null;
        this.pw = null;
        this.name = null;
        this.addr = null;
        this.phone = null;
        this.email = null;
        this.profile = null;
        this.admin = null;
        this.profileName = null;
    }
}
