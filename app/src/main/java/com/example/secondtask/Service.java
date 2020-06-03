package com.example.secondtask;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Service extends android.app.Service {
    private final String TAG = "Weather.WeatherService";
    private final Api api = ApiBuilder.createApi();
    private LocationManager locationManager;

    @Nullable
    private CurrentWeather currentWeather;

    @Override
    public void onCreate() {
        super.onCreate();

        Notification notification = createNotification();
        startForeground(1, notification);
        setupLocation();
    }

    @Override
    public void onDestroy() {
        locationManager.removeUpdates(locationListener);
        super.onDestroy();
    }

    private Notification createNotification() {
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("WeatherChannel", "My channel",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("My channel description");
            channel.enableLights(false);
            channel.setLightColor(Color.WHITE);
            channel.enableVibration(false);
            channel.setSound(null, null);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "WeatherChannel");
        builder.setSmallIcon(R.drawable.ic_stat_name);

        if (currentWeather == null) {
            builder.setContentTitle(getString(R.string.title_updating_weather));
            builder.setContentText(getString(R.string.text_updating_weather));
        } else {
            builder.setContentTitle(getString(
                    R.string.title_current_weather,
                    (int) currentWeather.getCurrent().getTemperature(),
                    currentWeather.getLocation().getName()));
            builder.setContentText(String.valueOf(currentWeather.getCurrent().getCloudcover()));
        }
        builder.setOngoing(true);

        Intent mainIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                mainIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        builder.setContentIntent(pendingIntent);

        return builder.build();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("MissingPermission")
    private void setupLocation() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, true);

        Log.v(TAG, "Provider: " + bestProvider);

        if (bestProvider != null) {
            Location lastKnownLocation = locationManager.getLastKnownLocation(bestProvider);

            Log.v(TAG, "Last location: " + lastKnownLocation);
            assert lastKnownLocation != null;
            getCurrentWeather(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
            locationManager.requestLocationUpdates(
                    bestProvider,
                    10000,
                    10,
                    locationListener
            );
        }
    }

    private final LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            Log.v(TAG, "Location changed: " + location);
            getCurrentWeather(location.getLatitude(), location.getLongitude());
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.v(TAG, "Status changed: " + provider + ", status: " + status);
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.v(TAG, "Provider enabled: " + provider);
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.v(TAG, "Provider disabled: " + provider);
        }

    };

    private void setCurrentWeather() {
        Notification notification = createNotification();
        startForeground(1, notification);
        Intent intent = new Intent(MainActivity.G_WEATHER);
        intent.putExtra(MainActivity.EXTRA_CURRENT_WEATHER, currentWeather);

        sendBroadcast(intent);

        SharedPreferences sharedPreferences = getSharedPreferences("weather", MODE_PRIVATE);
        sharedPreferences
                .edit()
                .putLong("last_update", System.currentTimeMillis())
                .apply();
    }

    private void getCurrentWeather(double lat, double lon) {
        Call<CurrentWeather> call = api.getCurrentWeather(Constants.API_KEY, lat + "," + lon);
        call.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(@NonNull Call<CurrentWeather> call, @NonNull Response<CurrentWeather> response) {
                if (response.isSuccessful()) {
                    CurrentWeather currentWeather = response.body();

                    Log.i(TAG, "Got weather: " + currentWeather);

                    Service.this.currentWeather = currentWeather;

                    setCurrentWeather();
                } else {
                    Log.e(TAG, "Failed to get current weather. Code: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<CurrentWeather> call, @NonNull Throwable t) {
                Log.e(TAG, "Failed to get current weather: " + t.getMessage());
            }
        });
    }
}
