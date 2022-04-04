package murasaki;

import org.approvaltests.awt.AwtApprovals;
import org.approvaltests.reporters.UseReporter;
import org.junit.jupiter.api.Test;

import java.time.Duration;

class VisualizationTest {

    @Test
    @UseReporter(VerticalImageReporter.class)
    public void visualization() {
        var valueStream = new ValueStream(
                Duration.ofHours(6),
                () -> Duration.ofHours(3),
                () -> Duration.ofHours(2),
                () -> Duration.ofMinutes(0));
        AwtApprovals.verify(valueStream);
    }

}
