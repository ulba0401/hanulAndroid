package com.example.hanulproject.vo;

import java.io.Serializable;

public class StatusVO implements Serializable {
    String id, light, secure, weather;
    int water, temper, dust;

    public StatusVO () {}
    public StatusVO(String id, String light, String secure, String weather, int water, int temper, int dust) {
        this.id = id;
        this.light = light;
        this.secure = secure;
        this.weather = weather;
        this.water = water;
        this.temper = temper;
        this.dust = dust;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public String getSecure() {
        return secure;
    }

    public void setSecure(String secure) {
        this.secure = secure;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getTemper() {
        return temper;
    }

    public void setTemper(int temper) {
        this.temper = temper;
    }

    public int getDust() {
        return dust;
    }

    public void setDust(int dust) {
        this.dust = dust;
    }



}
