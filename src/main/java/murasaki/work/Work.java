package murasaki.work;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

class Work {

    final String name;
    final Duration duration;
    final List<Activity> history;

    Work(String name, Duration duration) {
        this(name, duration, new ArrayList<>());
    }

    Work(String name, Duration duration, List<Activity> history) {
        this.name = name;
        this.duration = duration;
        this.history = history;
    }

    Work perform(ActivityType activityType, Duration duration) {
        if (activityType == ActivityType.WORK) {
            if (this.duration.compareTo(duration) < 0) {
                duration = this.duration;
            }
            return new Work(name, this.duration.minus(duration), appendHistory(activityType, duration));
        }
        return new Work(name, this.duration, appendHistory(activityType, duration));
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
                "name='" + name + '\'' +
                ", remaining=" + duration +
                ", history=" + history +
                '}';
    }


}
