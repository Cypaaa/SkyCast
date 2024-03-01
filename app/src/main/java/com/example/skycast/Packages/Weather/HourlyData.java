package com.example.skycast.Packages.Weather;

import com.google.gson.annotations.SerializedName;

public class HourlyData {
    @SerializedName("ICON")
    public String icon;
    @SerializedName("CONDITION")
    public String condition;
    @SerializedName("CONDITION_KEY")
    public String conditionKey;
    public int getGeneralizedCondition() {
        if (this.condition != null) {
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
        }
        return 8; // unknown
    }
    @SerializedName("TMP2m")
    public double tmp2m;
    @SerializedName("DPT2m")
    public double dpt2m;
    @SerializedName("WNDCHILL2m")
    public double wndChill2m;
    @SerializedName("HUMIDEX")
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
    @SerializedName("HCDH")
    public String hcdc;
    @SerializedName("MCDC")
    public String mcdc;
    @SerializedName("LCDC")
    public String lcdc;
    @SerializedName("HGT0C")
    public int hgt0c;
    public int kindex;
    @SerializedName("CAPE180_0")
    public String cape180_0;
    @SerializedName("CIN180_0")
    public int cin180_0;
}