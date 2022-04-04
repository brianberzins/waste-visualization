package murasaki;

import com.spun.swing.Paintable;
import org.lambda.actions.Action0;

import java.awt.*;
import java.time.Duration;
import java.util.function.Supplier;

record ValueStreamFactory(Duration duration,
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

        var cutoff = size.width - PIXELS_BOARDER;
        var currentPixelX = PIXELS_BOARDER;

        while (currentPixelX < cutoff) {
            graphics.setColor(Color.GREEN);
            var nextPixelX = Math.min(currentPixelX + pixelsForDuration(timeToBlock.get()), cutoff);
            graphics.drawLine(currentPixelX, PIXELS_BOARDER, nextPixelX, PIXELS_BOARDER);
            currentPixelX = nextPixelX + 1;

            graphics.setColor(Color.ORANGE);
            nextPixelX = Math.min(currentPixelX + pixelsForDuration(timeToContextSwitch.get()), cutoff);
            graphics.drawLine(currentPixelX, size.height / 2, nextPixelX, size.height / 2);
            currentPixelX = nextPixelX + 1;

            graphics.setColor(Color.RED);
            nextPixelX = Math.min(currentPixelX + pixelsForDuration(timeToResolution.get()), cutoff);
            graphics.drawLine(currentPixelX, size.height - PIXELS_BOARDER, nextPixelX, size.height - PIXELS_BOARDER);
            currentPixelX = nextPixelX + 1;
        }
    }

    @Override
    public void registerRepaint(Action0 repaint) {}
}
