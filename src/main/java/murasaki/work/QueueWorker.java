package murasaki.work;

import java.time.Duration;

interface QueueWorker {
    boolean addWork(Work work);

    Duration perform(Duration duration);
}
