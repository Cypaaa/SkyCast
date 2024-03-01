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
            case "Ciel voilé":
                return 0; // sunny
            case "Nuit claire":
            case "Nuit bien dégagée":
            case "Nuit légèrement voilée":
            case "Nuit faiblement orageuse":
                return 1; // night
            case "Averses de pluie faible":
            case "Pluie modérée":
            case "Pluie faible":
            case "Pluie forte":
                return 2; // rainy
            case "Averses de neige faible":
            case "Pluie et neige mêlée forte":
            case "Pluie et neige mêlée modérée":
            case "Pluie et neige mêlée faible":
            case "Nuit avec averses de neige faible":
            case "Neige forte":
            case "Neige modérée":
            case "Neige faible":
                return 3; // snowy
            case "Nuit avec averses":
            case "Couvert avec averses":
            case "Averses de pluie modérée":
            case "Averses de pluie forte":
                return 4; // stormy
            case "Fortement nuageux":
            case "Orage modéré":
            case "Faiblement orageux":
            case "Fortement orageux":
            case "Brouillard":
                return 5; // cloudy
            case "Stratus":
            case "Eclaircies":
            case "Stratus se dissipant":
            case "Faiblement nuageux":
            case "Développement nuageux":
            case "Faibles passages nuageux":
                return 6; // cloudy sunny
            case "Nuit nuageuse":
            case "Nuit claire et stratus":
            case "Nuit avec développement nuageux":
                return 7; // cloudy night
        }
        return 8; // unknown
    }
    public String icon;
    @SerializedName("icon_big")
    public String iconBig;
}