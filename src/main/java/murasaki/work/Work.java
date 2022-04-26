package murasaki.work;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

class Work {

    Duration remaining;
    List<Activity> history;

    Work(Duration remaining) {
        this(remaining, new ArrayList<>());
    }

    Work(Duration remaining, List<Activity> history) {
        this.remaining = remaining;
        this.history = history;
    }

    Duration perform(ActivityType activityType, Duration duration) {
        if (remaining.isZero()) {
            return Duration.ZERO;
        }
        if (activityType == ActivityType.WORK) {
            if (remaining.compareTo(duration) < 0) {
                duration = remaining;
            }
            remaining = remaining.minus(duration);
        }
        var lastIndex = history.size() - 1;
        if (0 <= lastIndex && history.get(lastIndex).type() == activityType) {
            history.set(lastIndex, new Activity(activityType, history.get(lastIndex).duration().plus(duration)));
        } else {
            history.add(new Activity(activityType, duration));
        }
        return duration;
    }

    @Override
    public String toString() {
        return "remaining=" + remaining + "\n" +
                "history=[" + "\n  " +
                String.join(",\n  ", history.stream().map(Activity::toString).toList()) + "\n" +
                "]";
    }

}
