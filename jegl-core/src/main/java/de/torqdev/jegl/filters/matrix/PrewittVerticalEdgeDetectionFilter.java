package de.torqdev.jegl.filters.matrix;

import org.kohsuke.MetaInfServices;

/**
 * Created by jonas on 26.05.17.
 */
@MetaInfServices
public class PrewittVerticalEdgeDetectionFilter extends AbstractEdgeDetectorFilter {
    private static final float[] PREWITT_OPERATOR = new float[]{
            // @formatter:off
            -1, 0, 1,
            -1, 0, 1,
            -1, 0, 1
            // @formatter:on
    };
    private static final float FACTOR = 1F;

    protected PrewittVerticalEdgeDetectionFilter() {
        super(PREWITT_OPERATOR, FACTOR);
    }
}


