package murasaki;

import java.time.Duration;
import java.util.ArrayList;
import java.util.function.Supplier;

class ValueStreamGenerator {

    Supplier<Duration> timeToComplete;
    Supplier<Duration> timeToBlock;
    Supplier<Duration> timeToResolution;

    ValueStreamGenerator timeToComplete(Supplier<Duration> timeToComplete) {
        this.timeToComplete = timeToComplete;
        return this;
    }

    ValueStreamGenerator timeToBlock(Supplier<Duration> timeToBlock) {
        this.timeToBlock = timeToBlock;
        return this;
    }

    ValueStreamGenerator timeToResolution(Supplier<Duration> timeToResolution) {
        this.timeToResolution = timeToResolution;
        return this;
    }

    ValueStream build() {
        var activities = new ArrayList<Activity>();
        Duration workRemaining = timeToComplete.get();
        while (0 < workRemaining.getSeconds()) {
            var timeToNextBlock = timeToBlock.get();
            if (timeToNextBlock.compareTo(workRemaining) < 0) {
                activities.add(new Activity(ActivityType.PRODUCTIVE, timeToNextBlock));
                activities.add(new Activity(ActivityType.BLOCKED, timeToResolution.get()));
            } else {
                activities.add(new Activity(ActivityType.PRODUCTIVE, timeToNextBlock));
            }
            workRemaining = workRemaining.minus(timeToNextBlock);
        }
        return new ValueStream(activities);
    }
}
