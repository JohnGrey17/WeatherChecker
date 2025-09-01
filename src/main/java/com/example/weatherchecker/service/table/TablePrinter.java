package com.example.weatherchecker.service.table;

import com.example.weatherchecker.model.Forecast;

import java.util.List;

public final class TablePrinter {

    public void printCompact(String date, List<Forecast> rows) {
        int cityW = Math.max("City".length(),
                rows.stream().map(r -> r.getCity().length()).max(Integer::compareTo).orElse(4));
        int dataW = Math.max(date.length(), rows.stream().map(this::cell)
                .map(String::length).max(Integer::compareTo).orElse(10));

        String border = "+" + "-".repeat(cityW + 2) + "+" + "-".repeat(dataW + 2) + "+";
        System.out.println(border);
        System.out.printf("| %-" + cityW + "s | %-" + dataW + "s |%n", "City", date);
        System.out.println(border);
        for (Forecast r : rows) {
            System.out.printf("| %-" + cityW + "s | %-" + dataW + "s   |%n",
                    r.getCity(), cell(r));
        }
        System.out.println(border);
    }

    private String cell(Forecast r) {
        if (r.getMinimumTempCelsius() == null) return r.getWindDirection(); // ERR текст
        String hum = r.getHumidity() == null ? "?" : r.getHumidity().toString();
        return String.format("min %.1f°C | max %.1f°C | hum %s%% | wind %.1f kph %s",
                r.getMinimumTempCelsius(), r.getMaximumTempCelsius(),
                hum, r.getWindSpeedKph(), r.getWindDirection());
    }
}
