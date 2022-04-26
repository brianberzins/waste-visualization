package murasaki.work;

import java.time.Duration;
import java.util.ArrayList;

class Worker implements QueueWorker {
    ArrayList<Work> workList;

    Worker() {
        this(new ArrayList<>());
    }

    Worker(ArrayList<Work> work) {
        workList = work;
    }

    boolean addWork(Work work) {
        return workList.add(work);
    }

    Duration perform(Duration duration) {
        if (duration.compareTo(Duration.ZERO) <= 0) {
            return Duration.ZERO;
        }
        var firstIncomplete = workList.stream().filter(w -> !w.isComplete()).findFirst();
        if (firstIncomplete.isEmpty()) {
            return Duration.ZERO;
        }
        var currentWork = firstIncomplete.get();
        var workedDuration = currentWork.perform(ActivityType.WORK, duration);
        workList.stream().filter(w -> w != currentWork).forEach(x -> x.perform(ActivityType.WAIT, workedDuration));

        return workedDuration.plus(perform(duration.minus(workedDuration)));
    }
}
