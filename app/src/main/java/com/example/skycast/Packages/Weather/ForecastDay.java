package com.example.skycast.Packages.Weather;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ForecastDay {
    public String date;
    @SerializedName("day_short")
    public String dayShort;
    @SerializedName("day_long")
    public String dayLong;
    @SerializedName("tmin")
    public int tmin;
    @SerializedName("tmax")
    public int tmax;
    @SerializedName("condition")
    public String condition;
    @SerializedName("condition_key")
    public String conditionKey;
    @SerializedName("icon")
    public String icon;
    @SerializedName("icon_big")
    public String iconBig;
    @SerializedName("hourly_data")
    public Map<String, HourlyData> hourlyData;
}