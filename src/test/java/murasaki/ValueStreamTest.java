package murasaki;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class ValueStreamTest {

    @Test
    void testValueSteam() {
        var valueStream = new ValueStreamGenerator()
                .timeToComplete(     () -> Duration.ofHours(6))
                .timeToBlock(        () -> Duration.ofHours(2))
                .timeToResolution(   () -> Duration.ofHours(1))
                .build();
        assertEquals(Duration.ofHours(8), valueStream.toComplete());
    }


//    Duration duration,
//    Supplier<Duration> timeToBlock,
//    Supplier<Duration> timeToResolution,
//    Supplier<Duration> timeToContextSwitch
//
//                    Duration.ofHours(6),
//                            () -> Duration.ofHours(3),
//                            () -> Duration.ofHours(2),
//                            () -> Duration.ofMinutes(5));

}