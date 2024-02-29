package com.example.skycast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.location.Address;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.skycast.Packages.Coordinates.Coordinates;
import com.example.skycast.Packages.Location.Location;
import com.example.skycast.Packages.Weather.Weather;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Location.RequestPermission(this, this);

        Location.GetLocation(this, this, location -> {
            String cityName = Location.GetCityName(this, location);

            if (cityName != "") {
                Weather.CallCoordinates(
                        this,
                        new Coordinates(location.getLatitude(), location.getLongitude()),
                        (weatherData) -> {
                            TextView mainText = findViewById(R.id.main_text);
                            if (mainText != null ) {
                                mainText.setText(cityName);
                            }
                        },
                        (error) -> {
                            Toast.makeText(this, "Error getting weather: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                );
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Location.FINE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, execute your code here
                // For example, call GetLocation() method
                Location.GetLocation(this, this, location -> {
                    // Handle location retrieval here
                });
            } else {
                // Permission denied, handle accordingly (e.g., show a message to the user)
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}