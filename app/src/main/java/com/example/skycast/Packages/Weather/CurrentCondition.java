package com.example.skycast.Packages.Weather;

import com.google.gson.annotations.SerializedName;

public class CurrentCondition {
    public String date;
    public String hour;
    public int tmp;
    @SerializedName("wnd_spd")
    public int wndSpd;
    @SerializedName("wnd_gust")
    public int wndGust;
    @SerializedName("wnd_dir")
    public String wndDir;
    public double pressure;
    public int humidity;
    public String condition;
    @SerializedName("condition_key")
    public String conditionKey;
    public String icon;
    @SerializedName("icon_big")
    public String iconBig;
}