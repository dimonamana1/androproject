package com.example.secondtask;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class SecondActivity extends AppCompatActivity {
    CurrentWeather currentWeather;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        Bundle arguments = getIntent().getExtras();
        assert arguments != null;
        currentWeather = arguments.getParcelable("currentWeather");

        ImageView icon;
        TextView city;
        TextView temperature;
        TextView windSpeed;
        TextView windDegree;
        TextView pressure;
        TextView humidity;
        TextView uvIndex;
        TextView visibility;

        icon = findViewById(R.id.icon);
        city = findViewById(R.id.city);
        temperature = findViewById(R.id.temperature);
        windSpeed = findViewById(R.id.windSpeed);
        windDegree = findViewById(R.id.windDegree);
        pressure = findViewById(R.id.pressure);
        humidity = findViewById(R.id.humidity);
        uvIndex = findViewById(R.id.uvIndex);
        visibility = findViewById(R.id.visibility);

        Picasso.with(getBaseContext()).load(currentWeather.getCurrent().getWeather_icons()[0]).into(icon);
        city.setText(currentWeather.getLocation().getName());
        temperature.setText(String.valueOf(currentWeather.getCurrent().getTemperature()));
        windSpeed.setText("Wind speed: " + currentWeather.getCurrent().getWind_speed());
        windDegree.setText("Wind degree: " + currentWeather.getCurrent().getWind_degree());
        pressure.setText("Pressure : " + currentWeather.getCurrent().getPressure());
        humidity.setText("Humidity : " + currentWeather.getCurrent().getHumidity());
        uvIndex.setText("Uv Index: " + currentWeather.getCurrent().getUv_index());
        visibility.setText("Visibility: " + currentWeather.getCurrent().getVisibility());
    }
}
