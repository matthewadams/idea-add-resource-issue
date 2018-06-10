package blackbox;

import dummy.DateTimeTestUtils;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.time.Instant;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BlackBoxTest {

    @Test
    public void testSharedTestSource() {
        ZoneOffset expected =
                ZoneOffset.ofHours(
                        DateTimeTestUtils.ET.getRules().isDaylightSavings(Instant.now()) ? -4 : -5);
        assertEquals(expected, DateTimeTestUtils.getCurrentEasternTimeZoneOffset());
    }

    @Test
    public void testLoadResource() {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("foo.json");
        assertNotNull(stream);
    }
}
