package de.torqdev.jegl.filters.matrix;

import de.torqdev.jegl.filters.ImageFilter;
import org.kohsuke.MetaInfServices;

/**
 * Created by jonas on 26.05.17.
 */
@MetaInfServices(value = ImageFilter.class)
public class ScharrVerticalEdgeDetectionFilter extends AbstractEdgeDetectorFilter {
    private static final float[] scharrOperator = new float[]{3, 0, -3, 10, 0, -10, 3, 0, -3};
    private static final float factor = 1F;

    public ScharrVerticalEdgeDetectionFilter() {
        super(scharrOperator, factor);
    }
}
