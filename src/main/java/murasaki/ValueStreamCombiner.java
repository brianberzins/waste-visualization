package murasaki;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

class ValueStreamCombiner {
    static List<ValueStream> combine(List<ValueStream> valueStreams) {
        var results = new ArrayList<ValueStream>();
        for (int i = 0; i < valueStreams.size(); i++) {
            results.add(new ValueStream(new ArrayList<>()));
        }

        var currentDuration = Duration.ZERO;
        for (int i = 0; i < valueStreams.size(); i++) {
            results.set(i, valueStreams.get(i).copy());
            results.get(i).activities().add(0, new Activity(ActivityType.WAITING, currentDuration));
            currentDuration = currentDuration.plus(results.get(i).duration());
        }
        results.get(0).activities().remove(0);
        return results;
    }

}
