package murasaki.work;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

class WorkTest {

    @Test
    void testWork() {
        var work = new Work(Duration.ofHours(8));
        work = work.perform(ActivityType.WORK, Duration.ofHours(3));
        work = work.perform(ActivityType.WORK, Duration.ofHours(4));
        work = work.perform(ActivityType.WAIT, Duration.ofHours(5));
        work = work.perform(ActivityType.WAIT, Duration.ofHours(6));
        Approvals.verify(work);
    }

    @Test
    void testWorkWhenCompete() {
        var work = new Work(Duration.ofHours(0));
        work = work.perform(ActivityType.WORK, Duration.ofHours(3));
        work = work.perform(ActivityType.WAIT, Duration.ofHours(3));
        assertTrue(work.history.isEmpty());
    }

}