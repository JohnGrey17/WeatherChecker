package com.example.weatherchecker.service.inputParser;

import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;

public final class ImputArgsParser {

    private static final List<String> DEFAULT_LIST_OF_CITIES = List.of("Chisinau", "Madrid", "Kyiv", "Amsterdam");

    public List<String> parseCities(String[] args) {
        for (String a : args == null ? new String[0] : args) {
            if (a == null) continue;
            if ("-h".equals(a) || "--help".equals(a)) {
                printUsageAndExit();
            }
            if (a.startsWith("--cities=")) {
                String raw = a.substring("--cities=".length());
                var list = Arrays.stream(raw.split(","))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .map(s -> s.replace('_', ' '))
                        .distinct()
                        .collect(Collectors.toList());
                if (!list.isEmpty()) return list;
            }
        }
        return DEFAULT_LIST_OF_CITIES;
    }

    private void printUsageAndExit() {
        System.out.println("Usage:");
        System.out.println("  --cities=City1,City2,City3");
        System.out.println("Examples:");
        System.out.println("  ./gradlew run --args=\"--cities=Kyiv,Madrid\"");
        System.out.println("  ./gradlew run --args=\"--cities=New_York,Amsterdam\"");
        System.exit(0);
    }
}
