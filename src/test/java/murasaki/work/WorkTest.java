package murasaki.work;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WorkTest {

    @Test
    void testWork() {
        var work = new Work(Duration.ofHours(8));
        assertEquals(Duration.ofHours(3), work.perform(ActivityType.WORK, Duration.ofHours(3)));
        assertEquals(Duration.ofHours(4), work.perform(ActivityType.WAIT, Duration.ofHours(4)));
        assertEquals(Duration.ofHours(1), work.perform(ActivityType.WAIT, Duration.ofHours(1)));
        assertEquals(Duration.ofHours(5), work.perform(ActivityType.WORK, Duration.ofHours(6)));
        assertEquals(Duration.ofHours(0), work.perform(ActivityType.WAIT, Duration.ofHours(7)));
        assertEquals(Duration.ofHours(0), work.perform(ActivityType.WORK, Duration.ofHours(1)));
        Approvals.verify(work);
    }

}