package de.torqdev.jegl.filters.matrix;

import org.kohsuke.MetaInfServices;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
@MetaInfServices
public class SobelHorizontalEdgeDetectionFilter extends AbstractEdgeDetectorFilter {
    private static final float[] sobelOperator = new float[]{
            // @formatter:off
            1, 2, 1,
            0, 0, 0,
            -1, -2, -1,
            // @formatter:on
    };
    private static final float factor = 1F;

    public SobelHorizontalEdgeDetectionFilter() {
        super(sobelOperator, factor);
    }
}
