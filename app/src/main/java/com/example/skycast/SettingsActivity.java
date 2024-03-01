package com.example.skycast;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Activity activity = this;
        ((ImageView)findViewById(R.id.settings_back)).setOnClickListener(l -> {
            activity.finish();
        });
    }
}