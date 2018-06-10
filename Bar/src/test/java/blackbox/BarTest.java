package blackbox;

import dummy.DateTimeTestUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.time.Instant;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BarTest {

    @Test
    public void testSharedTestSource() {
        ZoneOffset expected =
                ZoneOffset.ofHours(
                        DateTimeTestUtils.ET.getRules().isDaylightSavings(Instant.now()) ? -4 : -5);
        assertEquals(expected, DateTimeTestUtils.getCurrentEasternTimeZoneOffset());
    }

    @Test
    public void testLoadResource() throws IOException {
        String s = IOUtils.resourceToString("/bar.json", Charset.forName("UTF-8"));
        assertEquals("\"bar\"", s);
    }
}
