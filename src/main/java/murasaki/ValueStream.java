package murasaki;

import com.spun.swing.Paintable;
import org.lambda.actions.Action0;

import java.awt.*;
import java.time.Duration;
import java.util.function.Supplier;

record ValueStream(Duration duration,
                   Supplier<Duration> timeToBlock,
                   Supplier<Duration> timeToResolution,
                   Supplier<Duration> timeToContextSwitch) implements Paintable {

    private static final int PIXELS_PER_5_MINUTES = 5;
    private static final int PIXELS_BOARDER = 10;
    private static final int PIXELS_PER_PERCENTAGE = 1;

    private int pixelsForDuration(Duration duration) {
        return (int) duration.getSeconds() * PIXELS_PER_5_MINUTES / 60;
    }

    @Override
    public Dimension getSize() {
        var width = pixelsForDuration(duration);
        var height = PIXELS_PER_PERCENTAGE * 100;
        return new Dimension(width + (2 * PIXELS_BOARDER), height + (2 * PIXELS_BOARDER));
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        var size = getSize();
        graphics.fillRect(0, 0, size.width, size.height);
        graphics.setColor(Color.GREEN);
        graphics.drawLine(PIXELS_BOARDER, PIXELS_BOARDER, PIXELS_BOARDER + pixelsForDuration(timeToBlock.get()), PIXELS_BOARDER);
    }

    @Override
    public void registerRepaint(Action0 repaint) {}
}
