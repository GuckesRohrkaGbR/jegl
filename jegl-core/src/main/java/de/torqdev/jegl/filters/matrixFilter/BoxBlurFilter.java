package de.torqdev.jegl.filters.matrixFilter;

import org.kohsuke.MetaInfServices;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
@MetaInfServices
public class BoxBlurFilter extends AbstractMatrixFilter {
    private static final float[] matrix = new float[]{
            // @formatter:off
            1, 1, 1,
            1, 1, 1,
            1, 1, 1
            // @formatter:on
    };

    private static final float factor = 1 / 9F;

    public BoxBlurFilter() {
        super(matrix, factor);
    }
}
