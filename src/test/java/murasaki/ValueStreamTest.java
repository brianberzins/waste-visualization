package murasaki;

import org.approvaltests.awt.AwtApprovals;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;

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

    @Test
    void testMultiValueSteam() {
        var valueStreams = ValueStreamCombiner.combine(List.of(valueStream, valueStream));
        assertNotNull(valueStreams);
        assertEquals(2, valueStreams.size());
        assertEquals(Duration.ofHours(10), valueStreams.get(0).toComplete());
        assertEquals(Duration.ofHours(17), valueStreams.get(1).toComplete());
    }
}