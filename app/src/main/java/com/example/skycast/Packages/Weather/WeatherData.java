package com.example.skycast.Packages.Weather;

import com.google.gson.annotations.SerializedName;

public class WeatherData {
    @SerializedName("city_info")
    public CityInfo cityInfo;

    @SerializedName("forecast_info")
    public ForecastInfo forecastInfo;

    @SerializedName("current_condition")
    public CurrentCondition currentCondition;

    @SerializedName("fcst_day_0")
    public ForecastDay fcstDay0;

    @SerializedName("fcst_day_1")
    public ForecastDay fcstDay1;

    @SerializedName("fcst_day_2")
    public ForecastDay fcstDay2;

    @SerializedName("fcst_day_3")
    public ForecastDay fcstDay3;

    @SerializedName("fcst_day_4")
    public ForecastDay fcstDay4;
}








