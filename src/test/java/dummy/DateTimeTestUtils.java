package dummy;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateTimeTestUtils {
    public static String ET_NAME = "America/New_York";
    public static ZoneId ET = ZoneId.of(ET_NAME);

    public static ZoneOffset getCurrentEasternTimeZoneOffset() {
        return ET.getRules().getOffset(Instant.now());
    }
}
