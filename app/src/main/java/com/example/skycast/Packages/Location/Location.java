package com.example.skycast.Packages.Location;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

public class Location {
    static public final int FINE_PERMISSION_CODE = 1;
    static android.location.Location LAST_KNOWN_LOCATION;

    // Request location permission if not given already
    // return true if a request is sent
    static public boolean RequestPermission(Context ctx, Activity activity) {
        if (ActivityCompat.checkSelfPermission(ctx, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ctx, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION}, Location.FINE_PERMISSION_CODE);
            return true;
        }
        return false;
    }

    static public String GetCityName(Context ctx, android.location.Location location) {
        Geocoder geocoder = new Geocoder(ctx, Locale.getDefault());
        List addresses = null;
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

        String cityName = "";
        if (addresses != null && addresses.size() > 0) {
            cityName = ((Address) addresses.get(0)).getLocality();
        }
        return cityName;
    }

    static public void GetLocation(Context ctx, Activity activity, Consumer<android.location.Location> callback) {
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(ctx);
        Location.RequestPermission(ctx, activity);

        // no need for Lint to check MissingPermission because it's already handled in an other function.
        // but Lint detects an error anyway because THIS function doesn't check directly by itself
        @SuppressLint("MissingPermission")
        Task<android.location.Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(location -> {
            if (location != null) {
                Location.LAST_KNOWN_LOCATION = location;
                if (callback != null) {
                    callback.accept(location);
                }
            }
        });
    }
}
