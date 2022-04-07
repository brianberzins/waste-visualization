package murasaki;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class ValueStreamTest {

    ValueStream valueStream = new ValueStreamGenerator()
            .timeToComplete(     () -> Duration.ofHours(7))
            .timeToBlock(        () -> Duration.ofHours(2))
            .timeToResolution(   () -> Duration.ofHours(1))
            .generate();

    @Test
    void testValueSteam() {
        assertEquals(Duration.ofHours(10), valueStream.duration());
    }

    @Test
    void testMultiValueSteam() {
        var valueStreams = ValueStreamCombiner.combine(Collections.nCopies(2, valueStream));
        assertNotNull(valueStreams);
        assertEquals(2, valueStreams.size());
        assertEquals(Duration.ofHours(10), valueStreams.get(0).duration());
        assertEquals(Duration.ofHours(20), valueStreams.get(1).duration());
        assertEquals(new Activity(ActivityType.WAITING, Duration.ofHours(10)), valueStreams.get(1).activities().get(0));
    }
}