package com.example.secondtask;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class ApiTest {
    private final Api api  = ApiBuilder.createApi();
@Test
    public void testCurrentWeather() throws IOException{
    Call<CurrentWeather> call = api.getCurrentWeather(Constants.API_KEY, "Odessa, Ukraine");
        Response <CurrentWeather> response = call.execute();
        Assert.assertTrue(response.isSuccessful());
        CurrentWeather body = response.body();
        Assert.assertNotNull(body);
    ForecastCurrent forecastCurrent = body.getCurrent();

    Assert.assertNotNull(forecastCurrent);

    // Проверим, что есть данные о ветре
    ForecastLocation forecastLocation = body.getLocation();

    Assert.assertNotNull(forecastLocation);
    }
}


