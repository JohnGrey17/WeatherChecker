package com.example.weatherchecker.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Forecast {
    private String city;
    private String date;
    private Double minimumTempCelsius;
    private Double maximumTempCelsius;
    private Integer humidity;
    private Double windSpeedKph;
    private String windDirection;
}