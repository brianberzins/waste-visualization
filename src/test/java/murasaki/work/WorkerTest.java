package murasaki.work;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WorkerTest {

    @Test
    void testWorker() {
        var worker = new SimpleWorker();
        assertTrue(worker.addWork(new Work(Duration.ofHours(8))));
        assertEquals(Duration.ofHours(3), worker.perform(Duration.ofHours(3)));
        assertTrue(worker.addWork(new Work(Duration.ofHours(9))));
        assertEquals(Duration.ofHours(7), worker.perform(Duration.ofHours(7)));
        assertEquals(Duration.ofHours(7), worker.perform(Duration.ofHours(12)));
        Approvals.verifyAll("work", worker.workList);
    }
}
