package com.example.weatherchecker.factory;

import com.example.weatherchecker.dto.ApiForecastResponseDto;
import com.example.weatherchecker.model.Forecast;

public class ForeCastDtoFactory {
    public Forecast fromForecastDay(String city, ApiForecastResponseDto.ForecastDay fd) {
        if (fd == null || fd.getDay() == null) return null;

        var d = fd.getDay();
        String windDir = pickWindDir(fd);

        return Forecast.builder()
                .city(city)
                .date(fd.getDate())
                .minimumTempCelsius(round1(d.getMinTempC()))
                .maximumTempCelsius(round1(d.getMaxTempC()))
                .humidity(roundInt(d.getAvgHumidity()))
                .windSpeedKph(round1(d.getMaxWindKph()))
                .windDirection(windDir != null ? windDir : "?")
                .build();
    }

    private String pickWindDir(ApiForecastResponseDto.ForecastDay fd) {
        if (fd.getHour() == null || fd.getHour().isEmpty()) return null;
        return fd.getHour().stream()
                .filter(h -> h.getTime() != null && h.getTime().endsWith("12:00"))
                .findFirst()
                .orElse(fd.getHour().get(fd.getHour().size() / 2))
                .getWindDir();
    }

    private Double round1(Double v) {
        return v == null ? null : Math.round(v * 10.0) / 10.0; }
    private Integer roundInt(Double v) {
        return v == null ? null : (int) Math.round(v); }
}
