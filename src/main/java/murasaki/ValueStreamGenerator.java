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
        Duration remaining = timeToComplete.get();
        while (0 < remaining.getSeconds()) {
            var toBlock = timeToBlock.get();
            var toResolution = timeToResolution.get();
            if (toBlock.compareTo(remaining) < 0) {
                activities.add(new Activity(ActivityType.PRODUCTIVE, toBlock));
                activities.add(new Activity(ActivityType.BLOCKED, toResolution));
            } else {
                activities.add(new Activity(ActivityType.PRODUCTIVE, remaining));
            }
            remaining = remaining.minus(toBlock);
        }
        return new ValueStream(activities);
    }
}
