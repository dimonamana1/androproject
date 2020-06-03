package com.example.secondtask;

import android.os.Parcel;
import android.os.Parcelable;

public class CurrentWeather implements Parcelable {

    private ForecastCurrent current;

    private ForecastLocation location;


    protected CurrentWeather(Parcel in) {
        current = in.readParcelable(ForecastCurrent.class.getClassLoader());
        location = in.readParcelable(ForecastLocation.class.getClassLoader());
    }

    public static final Creator<CurrentWeather> CREATOR = new Creator<CurrentWeather>() {
        @Override
        public CurrentWeather createFromParcel(Parcel in) {
            return new CurrentWeather(in);
        }

        @Override
        public CurrentWeather[] newArray(int size) {
            return new CurrentWeather[size];
        }
    };

    public ForecastCurrent getCurrent() {
        return current;
    }

    public ForecastLocation getLocation() {
        return location;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(current, flags);
        dest.writeParcelable(location, flags);
    }
}