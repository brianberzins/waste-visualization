package murasaki;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

record ValueStream(List<Activity> activities) {

    Duration duration() {
        return activities.stream().map(Activity::duration).reduce(Duration.ofSeconds(0), Duration::plus);
    }

    ValueStream copy() {
        return new ValueStream(new ArrayList<>(activities));
    }

}
