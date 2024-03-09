package com.example.skycast.Packages.Room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.skycast.Packages.Weather.HourlyData;

import java.util.Map;

@Entity(tableName = "Situation")
public class Situation {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "city_name")
    public String CityName;
    @ColumnInfo(name = "temperature")
    public String Temperature;
    @ColumnInfo(name = "condition_name")
    public String ConditionName;
    @ColumnInfo(name = "condition_img")
    public int ConditionImg;

    @ColumnInfo(name = "tmax")
    public int MaxTemperature;
    @ColumnInfo(name = "tmin")
    public int MinTemperature;
    @ColumnInfo(name = "wind_spd")
    public int WindSpeed;
    @ColumnInfo(name = "humidity")
    public int Humidity;

}
