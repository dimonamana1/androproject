package com.example.secondtask;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("current")
    Call<CurrentWeather> getCurrentWeather(
            @Query("access_key") String accessKey,
            @Query("query") String query
    );
}