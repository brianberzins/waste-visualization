package murasaki;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class ValueStreamTest {

    ValueStream valueStream = new ValueStreamGenerator()
            .timeToComplete(     () -> Duration.ofHours(7))
            .timeToBlock(        () -> Duration.ofHours(2))
            .timeToResolution(   () -> Duration.ofHours(1))
            .generate();

    @Test
    void testValueSteam() {
        assertEquals(Duration.ofHours(10), valueStream.toComplete());
    }
}