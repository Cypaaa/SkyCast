package com.example.skycast;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.skycast.Packages.FragmentHelper.FragmentHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {
    static final int MIN_DISTANCE = 150;
    private float pressStartLocation;
    private FragmentHelper fragmentHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        this.fragmentHelper = new FragmentHelper(getSupportFragmentManager(), R.id.settings_fragment_holder);
        this.fragmentHelper.ReplaceFragment(new SettingsFavouriteFragment(),  R.layout.fragment_settings_favourite);
        this.fragmentHelper.Register(new SettingsSettingsFragment(), R.layout.fragment_settings_settings);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.pressStartLocation = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float x2 = event.getX();
                float deltaX = x2 - this.pressStartLocation;

                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    BottomNavigationView bottomNavMenu = findViewById(R.id.settings_bottom_navigation_menu);

                    if (x2 > this.pressStartLocation) { // Left to Right swipe action
                        this.fragmentHelper.Replace(R.layout.fragment_settings_favourite);
                        bottomNavMenu.setSelectedItemId(R.id.settings_favourite);
                        Toast.makeText(this, "Left to Right swipe [Next]", Toast.LENGTH_SHORT).show ();
                    } else { // Right to left swipe action
                        bottomNavMenu.setSelectedItemId(R.id.settings_settings);
                        this.fragmentHelper.Replace(R.layout.fragment_settings_settings);
                        Toast.makeText(this, "Right to Left swipe [Previous]", Toast.LENGTH_SHORT).show ();
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}