package com.example.weatherchecker.service.weather;

import com.example.weatherchecker.api.WeatherApi;
import com.example.weatherchecker.dto.ApiForecastResponseDto;
import com.example.weatherchecker.factory.ForeCastDtoFactory;
import com.example.weatherchecker.model.Forecast;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class WeatherService {
    private final WeatherApi api;
    private final String apiKey;
    private final ForeCastDtoFactory factory;

    public WeatherService(WeatherApi api, String apiKey, ForeCastDtoFactory factory) {
        this.api = api;
        this.apiKey = apiKey;
        this.factory = factory;
    }

    public Result fetchTomorrowForCities(List<String> cities) throws Exception {
        List<Forecast> rows = new ArrayList<>();
        String dateHeader = null;

        for (String city : cities) {
            Response<ApiForecastResponseDto> resp = api.forecast(apiKey, city, 2, "no", "no").execute();

            if (!resp.isSuccessful() || resp.body() == null || resp.body().getForecast() == null) {
                rows.add(Forecast.builder()
                        .city(city)
                        .date("?")
                        .windDirection("ERR: HTTP " + (resp.isSuccessful() ? "null" : resp.code()))
                        .build());
                continue;
            }

            var fdays = resp.body().getForecast().getForecastday();
            if (fdays == null || fdays.isEmpty()) {
                rows.add(Forecast.builder().city(city).date("?")
                        .windDirection("ERR: No forecast").build());
                continue;
            }

            ApiForecastResponseDto.ForecastDay tomorrow =
                    fdays.size() > 1 ? fdays.get(1) : fdays.get(0);

            var dto = factory.fromForecastDay(city, tomorrow);
            if (dto == null) {
                rows.add(Forecast.builder().city(city).date("?")
                        .windDirection("ERR: Bad data").build());
            } else {
                rows.add(dto);
                if (dateHeader == null) dateHeader = dto.getDate();
            }
        }
        return new Result(dateHeader != null ? dateHeader : "Tomorrow", rows);
    }

    public record Result(String dateHeader, List<Forecast> rows) { }
}
