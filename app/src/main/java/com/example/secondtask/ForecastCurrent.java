package com.example.secondtask;

import android.os.Parcel;
import android.os.Parcelable;

public class ForecastCurrent implements Parcelable {
    protected ForecastCurrent(Parcel in) {
        observation_time = in.readString();
        temperature = in.readFloat();
        weather_code = in.readFloat();
        weather_icons = in.createStringArray();
        weather_description = in.createStringArray();
        wind_speed = in.readFloat();
        wind_degree = in.readFloat();
        wind_dir = in.readString();
        pressure = in.readFloat();
        precip = in.readFloat();
        humidity = in.readFloat();
        cloudcover = in.readFloat();
        feelslike = in.readFloat();
        uv_index = in.readFloat();
        visibility = in.readFloat();
    }

    public static final Creator<ForecastCurrent> CREATOR = new Creator<ForecastCurrent>() {
        @Override
        public ForecastCurrent createFromParcel(Parcel in) {
            return new ForecastCurrent(in);
        }

        @Override
        public ForecastCurrent[] newArray(int size) {
            return new ForecastCurrent[size];
        }
    };

    public String getObservation_time() {
        return observation_time;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getWeather_code() {
        return weather_code;
    }

    public String[] getWeather_icons() {
        return weather_icons;
    }

    public String[] getWeather_description() {
        return weather_description;
    }

    public float getWind_speed() {
        return wind_speed;
    }

    public float getWind_degree() {
        return wind_degree;
    }

    public String getWind_dir() {
        return wind_dir;
    }

    public float getPressure() {
        return pressure;
    }

    public float getPrecip() {
        return precip;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getCloudcover() {
        return cloudcover;
    }

    public float getFeelslike() {
        return feelslike;
    }

    public float getUv_index() {
        return uv_index;
    }

    public float getVisibility() {
        return visibility;
    }

    private String observation_time;
    private float temperature;
    private float weather_code;
    private String[] weather_icons;
    private String[] weather_description;
    private float wind_speed;
    private float wind_degree;
    private String wind_dir;
    private float pressure;
    private float precip;
    private float humidity;
    private float cloudcover;
    private float feelslike;
    private float uv_index;
    private float visibility;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(observation_time);
        dest.writeFloat(temperature);
        dest.writeFloat(weather_code);
        dest.writeStringArray(weather_icons);
        dest.writeStringArray(weather_description);
        dest.writeFloat(wind_speed);
        dest.writeFloat(wind_degree);
        dest.writeString(wind_dir);
        dest.writeFloat(pressure);
        dest.writeFloat(precip);
        dest.writeFloat(humidity);
        dest.writeFloat(cloudcover);
        dest.writeFloat(feelslike);
        dest.writeFloat(uv_index);
        dest.writeFloat(visibility);
    }
}
