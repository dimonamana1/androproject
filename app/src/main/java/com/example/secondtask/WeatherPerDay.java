package com.example.secondtask;

class WeatherPerDay {
    private final String city;
    private final String temperature;
    private final String iconUrl;
    private final String feelsLike;

    String getCity() {
        return city;
    }

    String getTemperature() {
        return temperature;
    }

    String getIconUrl() {
        return iconUrl;
    }

    String getFeelsLike(){
        return feelsLike;
    }

    WeatherPerDay(String city, String temperature, String iconUrl, String feelsLike) {
        this.city = city;
        this.temperature = temperature;
        this.iconUrl = iconUrl;
        this.feelsLike = feelsLike;
    }
}
