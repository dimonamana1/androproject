package com.example.secondtask;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int PERMISSION_CODE = 0;
    public static final String G_WEATHER = "com.example.secondtask.ACTION_GOT_WEATHER";
    public static final String EXTRA_CURRENT_WEATHER = "current_weather";
    private final WeatherReceiver weatherReceiver = new WeatherReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        SharedPreferences sharedPreferences = getSharedPreferences("lWeather", MODE_PRIVATE);
        long lastUpdate = sharedPreferences.getLong("lUpdate", 0);

        Log.i(TAG, "update " + lastUpdate);
    }

    @Override
    protected void onStart(){
        super.onStart();

        geoPermission();
        bindService(new Intent(this, Service.class),
                serviceConnection,
                Context.BIND_AUTO_CREATE);
        IntentFilter intentFilter = new IntentFilter(G_WEATHER);
        registerReceiver(weatherReceiver, intentFilter);
    }
    @Override
    protected void onStop() {
        unregisterReceiver(weatherReceiver);
        super.onStop();
    }

    private void startService() {
        Intent intent = new Intent(this, Service.class);
        startService(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startService();
            } else {
                geoPermission();
            }
        }
    }

    private void geoPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_CODE);
        } else {
            startService();
        }
    }

    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "Service connected!");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "Service disconnected!");
        }
    };
    private static class WeatherReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            intent.getAction();
        }
    }
}
