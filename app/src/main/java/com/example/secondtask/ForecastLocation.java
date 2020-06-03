package com.example.secondtask;

import android.os.Parcel;
import android.os.Parcelable;

public class ForecastLocation implements Parcelable {
    protected ForecastLocation(Parcel in) {
        name = in.readString();
        country = in.readString();
        region = in.readString();
        lat = in.readString();
        lon = in.readString();
        timezone_id = in.readString();
        localtime = in.readString();
        localtime_epoch = in.readFloat();
        utc_offset = in.readString();
    }

    public static final Creator<ForecastLocation> CREATOR = new Creator<ForecastLocation>() {
        @Override
        public ForecastLocation createFromParcel(Parcel in) {
            return new ForecastLocation(in);
        }

        @Override
        public ForecastLocation[] newArray(int size) {
            return new ForecastLocation[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public String getTimezone_id() {
        return timezone_id;
    }

    public String getLocaltime() {
        return localtime;
    }

    public float getLocaltime_epoch() {
        return localtime_epoch;
    }

    public String getUtc_offset() {
        return utc_offset;
    }

    private String name;
    private String country;
    private String region;
    private String lat;
    private String lon;
    private String timezone_id;
    private String localtime;
    private float localtime_epoch;
    private String utc_offset;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(country);
        dest.writeString(region);
        dest.writeString(lat);
        dest.writeString(lon);
        dest.writeString(timezone_id);
        dest.writeString(localtime);
        dest.writeFloat(localtime_epoch);
        dest.writeString(utc_offset);
    }
}