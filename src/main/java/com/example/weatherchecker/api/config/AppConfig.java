package com.example.weatherchecker.api.config;

import io.github.cdimascio.dotenv.Dotenv;

public class AppConfig {
    private final String apiKey;
    private final String baseUrl;

    public AppConfig() {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        this.apiKey = firstNonBlank(
                dotenv.get("WEATHER_API_KEY"),
                System.getenv("WEATHER_API_KEY"),
                System.getProperty("weather.api.key")
        );
        String fromEnv = firstNonBlank(
                dotenv.get("BASE_URL"),
                System.getenv("BASE_URL"),
                System.getProperty("base.url")
        );
        String def = "https://api.weatherapi.com/";
        this.baseUrl = ensureTrailingSlash(firstNonBlank(fromEnv, def));
    }
    public String getApiKey() { return apiKey; }
    public String getBaseUrl() { return baseUrl; }

    public void validateOrExit() {
        if (apiKey == null || apiKey.isBlank()) {
            System.err.println("ERROR: Set WEATHER_API_KEY (env, .env or -Dweather.api.key).");
            System.exit(1);
        }
    }

    private static String firstNonBlank(String... vals) {
        for (String v : vals) if (v != null && !v.isBlank()) return v;
        return null;
    }
    private static String ensureTrailingSlash(String s) {
        return s.endsWith("/") ? s : s + "/";
    }
}
