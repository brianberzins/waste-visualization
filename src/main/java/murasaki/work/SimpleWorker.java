package murasaki.work;

import java.time.Duration;
import java.util.ArrayList;

class SimpleWorker implements QueueWorker {
    ArrayList<Work> workList;

    SimpleWorker() {
        this(new ArrayList<>());
    }

    SimpleWorker(ArrayList<Work> work) {
        workList = work;
    }

    @Override
    public boolean addWork(Work work) {
        return workList.add(work);
    }

    @Override
    public Duration perform(Duration duration) {
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
