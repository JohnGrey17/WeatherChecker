
import com.example.weatherchecker.service.inputParser.ImputArgsParser;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class InputArgsParser {

    @Test
    void defaultCitiesWhenNoArg() {
        var p = new ImputArgsParser();
        var cities = p.parseCities(new String[]{});
        assertEquals(List.of("Chisinau", "Madrid", "Kyiv", "Amsterdam"), cities);
    }

    @Test
    void parseCustomCities() {
        var p = new ImputArgsParser();
        var cities = p.parseCities(new String[]{"--cities=Kyiv,Madrid,New_York"});
        assertEquals(List.of("Kyiv", "Madrid", "New York"), cities);
    }
}