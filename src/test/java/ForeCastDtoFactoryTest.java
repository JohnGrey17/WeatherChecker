import com.example.weatherchecker.dto.ApiForecastResponseDto;
import com.example.weatherchecker.factory.ForeCastDtoFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ForeCastDtoFactoryTest {

    @Test
    void mapsForecastDayToDto() {

        var day = new ApiForecastResponseDto.Day();
        day.setMinTempC(12.3);
        day.setMaxTempC(25.4);
        day.setAvgHumidity(60.0);
        day.setMaxWindKph(22.5);

        var hour = new ApiForecastResponseDto.Hour();
        hour.setTime("2025-09-02 12:00");
        hour.setWindDir("NW");
        hour.setWindKph(20.0);

        var fd = new ApiForecastResponseDto.ForecastDay();
        fd.setDate("2025-09-02");
        fd.setDay(day);
        fd.setHour(List.of(hour));

        // act
        var dto = new ForeCastDtoFactory().fromForecastDay("Kyiv", fd);

        // assert
        assertNotNull(dto);
        assertEquals("Kyiv", dto.getCity());
        assertEquals("2025-09-02", dto.getDate());
        assertEquals(12.3, dto.getMinimumTempCelsius(), 1e-9);
        assertEquals(25.4, dto.getMaximumTempCelsius(), 1e-9);
        assertEquals(60, dto.getHumidity()); // округлено до int в фабриці
        assertEquals(22.5, dto.getWindSpeedKph(), 1e-9);
        assertEquals("NW", dto.getWindDirection());
    }
}
