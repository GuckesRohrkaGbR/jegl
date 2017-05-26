package de.torqdev.jegl.filters.matrix;

import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.filters.ImageFilter;
import org.kohsuke.MetaInfServices;

import java.util.stream.IntStream;

import static java.lang.Math.*;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
@MetaInfServices
public class SobelEdgeDetectionFilter implements ImageFilter {
    private static final ImageFilter verticalSobel = new SobelVerticalEdgeDetectionFilter();
    private static final ImageFilter horizontalSobel = new SobelHorizontalEdgeDetectionFilter();

    @Override
    public FloatImage processImage(FloatImage image) {
        return applySobel(image);
    }

    private FloatImage applySobel(FloatImage image) {
        FloatImage vertical = verticalSobel.processImage(image);
        FloatImage horizontal = horizontalSobel.processImage(image);

        return combine(vertical, horizontal);
    }

    private FloatImage combine(FloatImage vertical, FloatImage horizontal) {
        FloatImage sobel = new FloatImage(vertical.getWidth(), vertical.getHeight(), 1);

        IntStream.range(0, sobel.getHeight()).forEach(
                y -> IntStream.range(0, sobel.getWidth()).forEach(
                        x -> sobel.setPixel(x, y, combinePixels(vertical.getPixel(x, y), horizontal.getPixel(x, y)))
                )
        );
        return sobel;
    }

    private float[] combinePixels(float[] verticalPixel, float[] horizontalPixel) {
        float newPixel = (float) sqrt(verticalPixel[0] * verticalPixel[0] + horizontalPixel[0] * horizontalPixel[0]);
        newPixel = normalize(newPixel);
        return new float[] { newPixel };
    }

    private float normalize(float newPixel) {
        return max(0F, min(1F, newPixel));
    }
}
