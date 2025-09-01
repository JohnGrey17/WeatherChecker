package com.example.weatherchecker;

import com.example.weatherchecker.api.config.ApiClient;
import com.example.weatherchecker.api.config.AppConfig;
import com.example.weatherchecker.factory.ForeCastDtoFactory;
import com.example.weatherchecker.service.inputParser.ImputArgsParser;
import com.example.weatherchecker.service.table.TablePrinter;
import com.example.weatherchecker.service.weather.WeatherService;

import java.util.List;

public class WeatherCli {

    public static void main(String[] args) throws Exception {
        AppConfig cfg = new AppConfig();
        cfg.validateOrExit();

        var api      = new ApiClient().create(cfg.getBaseUrl());
        var factory  = new ForeCastDtoFactory();
        var service  = new WeatherService(api, cfg.getApiKey(), factory);
        var printer  = new TablePrinter();
        var parser   = new ImputArgsParser();

        List<String> cities = parser.parseCities(args);

        var result = service.fetchTomorrowForCities(cities);

        printer.printCompact(result.dateHeader(), result.rows());
    }
}