import com.example.weatherchecker.model.Forecast;
import com.example.weatherchecker.service.table.TablePrinter;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TablePrinterTest {

    @Test
    void printsSomethingReasonable() {
        var rows = List.of(Forecast.builder()
                .city("Kyiv").date("2025-09-02")
                .minimumTempCelsius(10.0).maximumTempCelsius(20.0)
                .humidity(55).windSpeedKph(15.5).windDirection("NE")
                .build());

        var baos = new ByteArrayOutputStream();
        var oldOut = System.out;
        System.setOut(new PrintStream(baos));
        try {
            new TablePrinter().printCompact("2025-09-02", rows);
        } finally {
            System.setOut(oldOut);
        }

        String out = baos.toString();
        assertTrue(out.contains("Kyiv"));
        assertTrue(out.contains("2025-09-02"));
        assertTrue(out.contains("min 10.0°C"));
        assertTrue(out.contains("max 20.0°C"));
        assertTrue(out.contains("hum 55%"));
        assertTrue(out.contains("NE"));
    }
}