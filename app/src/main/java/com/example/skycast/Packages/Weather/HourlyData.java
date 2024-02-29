package com.example.skycast.Packages.Weather;

import com.google.gson.annotations.SerializedName;

public class HourlyData {
    public String icon;
    public String condition;
    @SerializedName("condition_key")
    public String conditionKey;
    @SerializedName("TMP2m")
    public double tmp2m;
    @SerializedName("DPT2m")
    public double dpt2m;
    @SerializedName("WNDCHILL2m")
    public double wndChill2m;
    public Double humidex;
    @SerializedName("RH2m")
    public int rh2m;
    public double prmsl;
    @SerializedName("APCPsfc")
    public double apcpSfc;
    @SerializedName("WNDSPD10m")
    public int wndSpd10m;
    @SerializedName("WNDGUST10m")
    public int wndGust10m;
    @SerializedName("WNDDIR10m")
    public int wndDir10m;
    @SerializedName("WNDDIRCARD10")
    public String wndDirCard10;
    @SerializedName("ISSNOW")
    public int isSnow;
    public String hcdc;
    public String mcdc;
    public String lcdc;
    @SerializedName("HGT0C")
    public int hgt0c;
    public int kindex;
    @SerializedName("CAPE180_0")
    public String cape180_0;
    @SerializedName("CIN180_0")
    public int cin180_0;
}