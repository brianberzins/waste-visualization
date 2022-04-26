package murasaki.work;

import java.time.Duration;

record Activity(ActivityType type, Duration duration) {

    @Override
    public String toString() {
        return type + " " + duration;
    }

}
