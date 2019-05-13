package com.example.hanulproject.vo;

import java.io.Serializable;

public class UserVO implements Serializable {
    String id, pw, name, addr, phone, email, profile, admin, isdel;

    public UserVO () {}

    public UserVO(String id, String pw, String name, String addr, String phone, String email, String profile, String admin) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.addr = addr;
        this.phone = phone;
        this.email = email;
        this.profile = profile;
        this.admin = admin;
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

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel;
    }
}
