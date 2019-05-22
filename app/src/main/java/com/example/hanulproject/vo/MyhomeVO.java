package com.example.hanulproject.vo;

import java.io.Serializable;

public class MyhomeVO implements Serializable {
    private String id;
    private String addr;
    int nx;
    int ny;

    public MyhomeVO () {}

    public MyhomeVO(String id, String addr) {
        this.id = id;
        this.addr = addr;
    }

    public MyhomeVO(String id, String addr, int nx, int ny) {
        this.id = id;
        this.addr = addr;
        this.nx = nx;
        this.ny = ny;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getNx() {
        return nx;
    }

    public void setNx(int nx) {
        this.nx = nx;
    }

    public int getNy() {
        return ny;
    }

    public void setNy(int ny) {
        this.ny = ny;
    }
}
