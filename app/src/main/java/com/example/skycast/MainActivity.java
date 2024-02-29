package com.example.skycast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skycast.Packages.Coordinates.Coordinates;
import com.example.skycast.Packages.Location.Location;
import com.example.skycast.Packages.Weather.Weather;
import com.example.skycast.Packages.Weather.WeatherData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!Location.RequestPermission(this, this)) {
            this.LoadUX(true);
        } else {
            this.LoadUX(false);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Location.FINE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, execute your code here
                this.LoadUX(true);
            } else {
                // Permission denied, handle accordingly (e.g., show a message to the user)
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void LoadUX(boolean loadLocation) {
        if (loadLocation) {
            this.LoadLocation();
        }

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("E dd/MM HH:mm", Locale.getDefault());
        TextView date = findViewById(R.id.date);
        date.setText(dateFormat.format(currentDate));
    }

    public void LoadLocation() {
        Location.GetLocation(this, this, location -> {
            String cityName = Location.GetCityName(this, location);
            if (cityName != "") {
                Weather.CallCoordinates(
                        this,
                        new Coordinates(location.getLatitude(), location.getLongitude()),
                        (weatherData) -> {
                            GifImageView gifImageView = findViewById(R.id.gifImageView);
                            TextView city_name = findViewById(R.id.city_name);
                            TextView degrees = findViewById(R.id.degrees);
                            TextView condition = findViewById(R.id.condition);
                            TextView tmax = findViewById(R.id.tmax);
                            TextView tmin = findViewById(R.id.tmin);
                            city_name.setText(cityName);
                            degrees.setText(String.valueOf(weatherData.currentCondition.tmp));
                            condition.setText(weatherData.currentCondition.condition);
                            tmax.setText(String.valueOf(weatherData.fcstDay0.tmax));
                            tmin.setText(String.valueOf(weatherData.fcstDay0.tmin));
                            gifImageView.setImageResource(weatherData.currentCondition.getGeneralizedCondition());
                        },
                        (error) -> {
                            Toast.makeText(this, "Error getting weather: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                );
            }
        });
    }
}