package de.torqdev.jegl.filters.matrix;

/**
 * Created by jonas on 26.05.17.
 */
public class ScharrVerticalEdgeDetectionFilter extends AbstractEdgeDetectorFilter {
    private static final float[] scharrOperator = new float[]{
            3, 0, -3,
            10, 0, -10,
            3, 0, -3
    };
    private static final float factor = 1F;

    protected ScharrVerticalEdgeDetectionFilter() {
        super(scharrOperator, factor);
    }
}
