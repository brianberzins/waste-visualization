package murasaki.work;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.time.Duration;

class WorkTest {

    @Test
    void testWork() {
        var work = new Work("example", Duration.ofHours(8));
        work = work.perform(ActivityType.WORK, Duration.ofHours(3));
        work = work.perform(ActivityType.WORK, Duration.ofHours(4));
        work = work.perform(ActivityType.WAIT, Duration.ofHours(5));
        work = work.perform(ActivityType.WAIT, Duration.ofHours(6));
        Approvals.verify(work);
    }

}