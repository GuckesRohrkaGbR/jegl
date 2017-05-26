package de.torqdev.jegl.filters.matrix;

import org.kohsuke.MetaInfServices;

/**
 * Created by jonas on 26.05.17.
 */
@MetaInfServices
public class PrewittHorizontalEdgeDetectionFilter extends AbstractEdgeDetectorFilter {
    private static final float[] PREWITT_OPERATOR = new float[]{-1, -1, -1, 0, 0, 0, 1, 1, 1};
    private static final float factor = 1F;

    protected PrewittHorizontalEdgeDetectionFilter() {
        super(PREWITT_OPERATOR, factor);
    }
}
