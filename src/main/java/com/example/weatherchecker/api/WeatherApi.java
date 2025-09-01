package com.example.weatherchecker.api;

import com.example.weatherchecker.dto.ApiForecastResponseDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("v1/forecast.json")
    Call<ApiForecastResponseDto> forecast(
            @Query("key") String key,
            @Query("q") String city,
            @Query("days") int days,
            @Query("aqi") String aqi,
            @Query("alerts") String alerts
    );
}


