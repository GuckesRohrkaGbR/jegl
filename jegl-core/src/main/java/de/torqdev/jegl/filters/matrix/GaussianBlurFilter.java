package de.torqdev.jegl.filters.matrix;

import de.torqdev.jegl.filters.ImageFilter;
import org.kohsuke.MetaInfServices;

/**
 * Created by lennart on 26.05.2017.
 */
@MetaInfServices(value = ImageFilter.class)
public class GaussianBlurFilter extends AbstractMatrixFilter {
    private static final float[] MATRIX = new float[]{
            // @formatter:off
            1, 2, 1,
            2, 4, 2,
            1, 2, 1,
            // @formatter:on
    };

    private static final float FACTOR = 1 / 16F;

    public GaussianBlurFilter() {
        super(MATRIX, FACTOR);
    }
}
