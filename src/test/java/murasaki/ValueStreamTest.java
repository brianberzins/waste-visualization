package murasaki;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class ValueStreamTest {

    @Test
    void testValueSteam() {
        var valueStream = new ValueStreamGenerator()
                .timeToComplete(     () -> Duration.ofHours(7))
                .timeToBlock(        () -> Duration.ofHours(2))
                .timeToResolution(   () -> Duration.ofHours(1))
                .build();
        assertEquals(Duration.ofHours(10), valueStream.toComplete());
    }

}