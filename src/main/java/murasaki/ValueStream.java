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

    ValueStream collapse() {
        var resultActivities = new ArrayList<Activity>();
        for (int i = 0; i < activities.size(); i++) {
            var currentType = activities.get(i).type();
            var currentDuration = activities.get(i).duration();
            for (int j = i + 1; j < activities.size() && currentType == activities.get(j).type(); j++) {
                currentDuration = currentDuration.plus(activities.get(j).duration());
            }
            resultActivities.add(new Activity(currentType, currentDuration));
        }
        return new ValueStream(resultActivities);
    }

}
