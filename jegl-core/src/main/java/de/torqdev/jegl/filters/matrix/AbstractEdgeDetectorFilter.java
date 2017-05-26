package de.torqdev.jegl.filters.matrix;

import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.filters.grayscale.AverageGrayscaleFilter;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by jonas on 26.05.17.
 */
public class AbstractEdgeDetectorFilter extends AbstractMatrixFilter {
    private static final AverageGrayscaleFilter GRAYSCALE_FILTER = new AverageGrayscaleFilter();

    protected AbstractEdgeDetectorFilter(float[] matrix, float factor) {
        super(matrix, factor);
    }

    @Override
    public FloatImage processImage(FloatImage image) {
        return super.processImage(GRAYSCALE_FILTER.processImage(image));
    }
    @Override
    protected float normalize(float color) {
        return (max(-1F, min(1F, color)) + 1F) / 2F;
    }
}
