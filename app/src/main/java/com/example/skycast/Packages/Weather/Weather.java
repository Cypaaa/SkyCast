package com.example.skycast.Packages.Weather;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.function.Consumer;
import com.android.volley.Request;
import com.example.skycast.Packages.Coordinates.Coordinates;
import com.google.gson.Gson;

public class Weather {
    static private final String URI = "https://www.prevision-meteo.ch/services/json/";

    // Get a city information by its name
    static public void CallCity(Context ctx, String city, Consumer<WeatherData> okCallback,
                                Response.ErrorListener errorCallback) {
        String url = Weather.URI + city;
        Weather.call(
                ctx,
                url,
                (response) -> {
                    okCallback.accept(Weather.StringToWeatherData(response));
                },
                errorCallback
        );
    }

    // Get a city information by its coordinates (long and lat)
    static public void CallCoordinates(Context ctx, Coordinates coordinates,
                                       Consumer<WeatherData> okCallback,
                                       Response.ErrorListener errorCallback) {
        String url = Weather.URI + "lat=" + coordinates.getLatitude() + "lng=" +coordinates.getLongitude();
        Weather.call(
                ctx,
                url,
                (response) -> {
                    okCallback.accept(Weather.StringToWeatherData(response));
                },
                errorCallback
        );
    }

    // Get the list of available cities
    static public void CallCityList(Context ctx, Consumer<String> okCallback,
                                    Response.ErrorListener errorCallback) {
        String url = Weather.URI + "list-cities";
        Weather.call(
                ctx,
                url,
                (response) -> {
                    okCallback.accept(response);
                },
                errorCallback
        );
    }

    // Make a call to the api and execute the handlers
    static private void call(Context ctx, String url, Response.Listener<String> okCallback,
                             Response.ErrorListener errorCallback) {
        RequestQueue queue = Volley.newRequestQueue(ctx);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                okCallback,
                errorCallback
        );
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    static private WeatherData StringToWeatherData(String httpResponse) {
        Gson gson = new Gson();
        return gson.fromJson(httpResponse, WeatherData.class);
    }
}
