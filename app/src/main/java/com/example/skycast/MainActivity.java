package com.example.skycast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.skycast.Packages.Weather.Weather;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Weather.CallCity(
                this,
                "Toulouse",
                (weatherData) -> {
                    TextView mainText = findViewById(R.id.main_text);
                    if (mainText != null ) {
                        mainText.setText(weatherData.cityInfo.name);
                    }
                },
                (error) -> {
                    Toast.makeText(this, "Error getting weather: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
        );
    }
}