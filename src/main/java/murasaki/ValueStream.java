package murasaki;

import java.time.Duration;
import java.util.List;

record ValueStream(List<Activity> activities) {

    Duration toComplete() {
        return activities.stream().map(Activity::duration).reduce(Duration.ofSeconds(0), Duration::plus);
    }
}
