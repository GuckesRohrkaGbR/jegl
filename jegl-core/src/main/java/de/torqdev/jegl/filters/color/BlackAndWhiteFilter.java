package de.torqdev.jegl.filters.color;

import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.filters.ImageFilter;
import de.torqdev.jegl.filters.grayscale.AbstractGrayscaleFilter;
import de.torqdev.jegl.filters.grayscale.AverageGrayscaleFilter;
import org.apache.commons.lang3.ArrayUtils;
import org.kohsuke.MetaInfServices;

import java.util.stream.IntStream;

/**
 * Simple filter that renders all colors above the given threshold white and all others black.
 *
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
@MetaInfServices
public class BlackAndWhiteFilter implements ImageFilter {
    private final static AverageGrayscaleFilter GRAYSCALE_FILTER = new AverageGrayscaleFilter();
    private final float threshold;

    public BlackAndWhiteFilter() {
        this(0.5F);
    }

    public BlackAndWhiteFilter(float threshold) {
        this.threshold = threshold;
    }

    @Override
    public FloatImage processImage(FloatImage image) {
        FloatImage gray = GRAYSCALE_FILTER.processImage(image);

        IntStream.range(0, gray.getHeight()).forEach(
                y -> IntStream.range(0, gray.getWidth()).forEach(
                        x -> {
                            gray.setPixel(x, y, new float[] {thresholdFilter(gray.getPixel(x, y)[0])});
                        }
                )
        );
        return gray;
    }

    private float thresholdFilter(float pixel) {
        return pixel < threshold ? 0F : 1F;
    }
}