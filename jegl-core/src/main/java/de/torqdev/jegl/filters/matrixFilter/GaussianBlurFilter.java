package de.torqdev.jegl.filters.matrixFilter;

import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.filters.ImageFilter;
import org.kohsuke.MetaInfServices;

import java.util.stream.IntStream;

/**
 * Created by lennart on 26.05.2017.
 */
@MetaInfServices
public class GaussianBlurFilter extends AbstractMatrixFilter {
    private static final float[] matrix = new float[]{
            // @formatter:off
            1, 2, 1,
            2, 4, 2,
            1, 2, 1,
            // @formatter:on
    };

    private static final float factor = 1 / 16F;

    protected GaussianBlurFilter(float[] matrix, float factor) {
        super(matrix, factor);
    }
}
