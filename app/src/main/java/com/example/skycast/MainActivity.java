package com.example.skycast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skycast.Adapters.HourlyAdapter;
import com.example.skycast.Packages.Coordinates.Coordinates;
import com.example.skycast.Packages.Location.Location;
import com.example.skycast.Packages.Room.Situation;
import com.example.skycast.Packages.Room.SituationDatabase;
import com.example.skycast.Packages.Weather.HourlyData;
import com.example.skycast.Packages.Weather.Weather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter hourlyAdapter;
    private RecyclerView recyclerView;
    private SituationDatabase situationDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RoomDatabase.Callback dbCallback = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }
        };

        situationDatabase = Room.databaseBuilder(getApplicationContext(),
                SituationDatabase.class,"SituationDB").addCallback(dbCallback).build();

        if (!Location.RequestPermission(this, this)) {
            this.LoadUX(true);
        } else {
            this.LoadUX(false);
        }

        Activity self = this;
        OnBackPressedDispatcher onBackPressedDispatcher = getOnBackPressedDispatcher();
        onBackPressedDispatcher.addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button press event here
                RequestClose(self);
            }
        });
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

        ((ImageView)findViewById(R.id.settings)).setOnClickListener(l -> {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        });

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("E MMMM dd '|' HH:mm", Locale.getDefault());
        TextView date_time = findViewById(R.id.date_time);
        date_time.setText(dateFormat.format(currentDate));
    }

    public void LoadLocation() {
        Location.GetLocation(this, this, location -> {
            String cityName = Location.GetCityName(this, location);
            Weather.CallCoordinates(
                    this,
                    new Coordinates(location.getLatitude(), location.getLongitude()),
                    (weatherData) -> {
                        Situation here = new Situation();
                        here.CityName = cityName;
                        here.ConditionName = weatherData.currentCondition.condition;
                        here.MaxTemperature = weatherData.fcstDay0.tmax;
                        here.MinTemperature = weatherData.fcstDay0.tmin;
                        here.ConditionImg = weatherData.currentCondition.getGeneralizedCondition();
                        here.Humidity = weatherData.currentCondition.humidity;
                        here.WindSpeed = weatherData.currentCondition.wndSpd;

                        addSituationInBackground(here);

                        TextView city_name = findViewById(R.id.city_name);
                        TextView temperature = findViewById(R.id.temperature);
                        TextView condition_name = findViewById(R.id.condition_name);
                        ImageView condition_img = findViewById(R.id.condition_img);
                        TextView tmax_min = findViewById(R.id.tmax_min);
                        TextView wind_spd = findViewById(R.id.wind_speed);
                        TextView humidity = findViewById(R.id.humidity);


                        city_name.setText(cityName); // city names
                        temperature.setText(getString( // temperature
                                R.string.temperature_format,
                                weatherData.currentCondition.tmp));
                        tmax_min.setText(String.format( // temperature min and max
                                getString(R.string.temperature_max_min_format),
                                Integer.toString(weatherData.fcstDay0.tmax),
                                Integer.toString(weatherData.fcstDay0.tmin)));
                        condition_name.setText(weatherData.currentCondition.condition);
                        switch (weatherData.currentCondition.getGeneralizedCondition()) {
                            case 0:
                                condition_img.setImageResource(R.drawable.sunny);
                                break;
                            case 1:
                                condition_img.setImageResource(R.drawable.night);
                                break;
                            case 2:
                                condition_img.setImageResource(R.drawable.rainy);
                                break;
                            case 3:
                                condition_img.setImageResource(R.drawable.snowy);
                                break;
                            case 4:
                                condition_img.setImageResource(R.drawable.storm);
                                break;
                            case 5:
                                condition_img.setImageResource(R.drawable.cloudy);
                                break;
                            case 6:
                                condition_img.setImageResource(R.drawable.cloudy_sunny);
                                break;
                            case 7:
                                condition_img.setImageResource(R.drawable.cloudy_night);
                                break;
                        }
                        wind_spd.setText(getString(R.string.number_kmh_format, weatherData.currentCondition.wndSpd));
                        humidity.setText(getString(R.string.number_percentage_format, weatherData.currentCondition.humidity));
                        this.LoadRecyclerView(weatherData.fcstDay0.hourlyData);
                    },
                    (error) -> {
                        Toast.makeText(this, "Error getting weather: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
            );
        });
    }


    public void LoadRecyclerView(Map<String, HourlyData> items) {
        this.recyclerView = findViewById(R.id.forecast_hours);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        this.hourlyAdapter = new HourlyAdapter(items);
        this.recyclerView.setAdapter(this.hourlyAdapter);
    }

    public void RequestClose(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Exit app");
        builder.setMessage("Are you sure you want to exit the app ? I have your IP, just so you know :).");

        Activity self = this;
        builder.setPositiveButton("exit", (dialog, which) -> self.finish());
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.create().show();
    }

    public void addSituationInBackground(Situation situation) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(() -> {
            situationDatabase.getSituationDAO().addSituation(situation);
            handler.post(() ->  {
                Toast.makeText(this, "City Added", Toast.LENGTH_SHORT).show();
            });
        });
    }

    public void getSituationInBackground(int id, Consumer<Situation> callback) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(() -> {
            Situation situation = situationDatabase.getSituationDAO().getSituation(id);
            handler.post(() ->  {
                callback.accept(situation);
            });
        });
    }
}