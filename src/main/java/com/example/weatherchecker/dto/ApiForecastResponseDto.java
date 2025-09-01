package com.example.weatherchecker.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class ApiForecastResponseDto {
    private Forecast forecast;

    @Data
    public static class Forecast {
        private List<ForecastDay> forecastday;
    }

    @Data
    public static class ForecastDay {
        private String date;
        private Day day;
        private List<Hour> hour;
    }

    @Data
    public static class Day {
        @SerializedName("mintemp_c")
        private Double minTempC;

        @SerializedName("maxtemp_c")
        private Double maxTempC;

        @SerializedName("avghumidity")
        private Double avgHumidity;

        @SerializedName("maxwind_kph")
        private Double maxWindKph;
    }

    @Data
    public static class Hour {
        private String time;

        @SerializedName("wind_kph")
        private Double windKph;

        @SerializedName("wind_dir")
        private String windDir;
    }
}
