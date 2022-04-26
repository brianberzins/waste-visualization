package murasaki.work;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

class Work {

    final Duration remaining;
    final List<Activity> history;

    Work(Duration remaining) {
        this(remaining, new ArrayList<>());
    }

    Work(Duration remaining, List<Activity> history) {
        this.remaining = remaining;
        this.history = history;
    }

    Work perform(ActivityType activityType, Duration duration) {
        if (activityType == ActivityType.WORK) {
            if (this.remaining.compareTo(duration) < 0) {
                duration = this.remaining;
            }
            return new Work(this.remaining.minus(duration), appendHistory(activityType, duration));
        }
        return new Work(this.remaining, appendHistory(activityType, duration));
    }

    List<Activity> appendHistory(ActivityType activityType, Duration duration) {
        var lastIndex = history.size() - 1;
        var copy = new ArrayList<>(history);
        if (0 <= lastIndex && history.get(lastIndex).type() == activityType) {
            copy.set(lastIndex, new Activity(activityType, history.get(lastIndex).duration().plus(duration)));
        } else {
            copy.add(new Activity(activityType, duration));
        }
        return copy;
    }

    @Override
    public String toString() {
        return "Work{" +
                "remaining=" + remaining +
                ", history=" + history +
                '}';
    }


}
