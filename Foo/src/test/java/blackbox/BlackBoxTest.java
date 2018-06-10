package blackbox;

import dummy.DateTimeTestUtils;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlackBoxTest {

    @Test
    public void test() {
        ZoneOffset expected =
                ZoneOffset.ofHours(
                        DateTimeTestUtils.ET.getRules().isDaylightSavings(Instant.now()) ? -4 : -5);
        assertEquals(expected, DateTimeTestUtils.getCurrentEasternTimeZoneOffset());
    }
}
