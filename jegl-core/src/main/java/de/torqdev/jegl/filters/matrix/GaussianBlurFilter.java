package de.torqdev.jegl.filters.matrix;

import org.kohsuke.MetaInfServices;

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

    public GaussianBlurFilter(float[] matrix, float factor) {
        super(matrix, factor);
    }
}
