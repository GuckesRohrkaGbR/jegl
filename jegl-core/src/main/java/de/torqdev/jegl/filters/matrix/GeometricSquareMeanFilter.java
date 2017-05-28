package de.torqdev.jegl.filters.matrix;

import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.filters.ImageFilter;

import static java.lang.Math.*;

/**
 * @author <a href="mailto:jonas.egert@torq-dev.de">Jonas Egert</a>
 * @version 1.0
 */
public class GeometricSquareMeanFilter implements ImageFilter {
    private final ImageFilter filter1;
    private final ImageFilter filter2;

    public GeometricSquareMeanFilter(ImageFilter filter1, ImageFilter filter2) {
        this.filter1 = filter1;
        this.filter2 = filter2;
    }

    private FloatImage applyFilter(FloatImage image) {
        FloatImage image1 = filter1.processImage(image);
        FloatImage image2 = filter2.processImage(image);

        return combine(image1, image2);
    }

    private FloatImage combine(FloatImage image1, FloatImage image2) {
        FloatImage filter = new FloatImage(image1.getWidth(), image1.getHeight(), 1);

        int bound1 = filter.getHeight();
        for (int y = 0; y < bound1; y++) {
            int bound = filter.getWidth();
            for (int x = 0; x < bound; x++) {
                filter.setPixel(x, y, combinePixels(image1.getPixel(x, y), image2.getPixel(x, y)));
            }
        }
        return filter;
    }

    private float[] combinePixels(float[] pixel1, float[] pixel2) {
        float newPixel = (float) sqrt(
                pixel1[0] * pixel1[0] + pixel2[0] * pixel2[0]);
        newPixel = normalize(newPixel);
        return new float[]{newPixel};
    }

    private float normalize(float newPixel) {
        return max(0F, min(1F, newPixel));
    }

    @Override
    public FloatImage processImage(FloatImage image) {
        return applyFilter(image);
    }
}

