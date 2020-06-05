package com.example.secondtask;

import android.graphics.ImageDecoder;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class WeatherPerDay {
    private final String city;
    private final String temperature;
    private final String iconUrl;
    private final String feelsLike;

    public String getCity() {
        return city;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getFeelsLike(){
        return feelsLike;
    }

    public WeatherPerDay(String city, String temperature, String iconUrl, String feelsLike) {
        this.city = city;
        this.temperature = temperature;
        this.iconUrl = iconUrl;
        this.feelsLike = feelsLike;
    }
}
