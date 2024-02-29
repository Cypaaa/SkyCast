package com.example.skycast.Packages.Weather;

import com.example.skycast.R;
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
    public int getGeneralizedCondition() {
        switch (this.condition) {
            case "Ensoleillé":
            case "Faibles passages nuageux":
            case "Eclaircies":
                return R.drawable.sun;
            case "Nuit claire":
            case "Nuit légèrement voilée":
            case "Nuit bien dégagée":
            case "Nuit claire et stratus":
            case "Nuit faiblement orageuse":
            case "Nuit avec développement nuageux":
                return R.drawable.night;
            case "Stratus":
            case "Stratus se dissipant":
            case "Brouillard":
                return R.drawable.mist;
            case "Ciel voilé":
            case "Nuit nuageuse":
            case "Faiblement nuageux":
            case "Fortement nuageux":
            case "Orage modéré":
            case "Faiblement orageux":
            case "Développement nuageux":
            case "Fortement orageux":
                return R.drawable.cloud;
            case "Averses de pluie faible":
            case "Averses de pluie modérée":
            case "Averses de pluie forte":
            case "Couvert avec averses":
            case "Nuit avec averses":
            case "Pluie modérée":
            case "Pluie faible":
            case "Pluie forte":
                return R.drawable.rain;
            case "Averses de neige faible":
            case "Pluie et neige mêlée forte":
            case "Pluie et neige mêlée modérée":
            case "Pluie et neige mêlée faible":
            case "Neige forte":
            case "Neige modérée":
            case "Neige faible":
            case "Nuit avec averses de neige faible":
                return R.drawable.snow;
        }
        return R.drawable.setrise;
    }
    public String icon;
    @SerializedName("icon_big")
    public String iconBig;
}