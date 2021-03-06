package de.torqdev.jegl.filters.matrix;

import de.torqdev.jegl.filters.ImageFilter;
import org.kohsuke.MetaInfServices;

/**
 * Created by jonas on 26.05.17.
 */
@MetaInfServices(value = ImageFilter.class)
public class PrewittHorizontalEdgeDetectionFilter extends AbstractEdgeDetectorFilter {
    private static final float[] PREWITT_OPERATOR = new float[]{
            // @formatter:off
            -1, -1, -1,
            0, 0, 0,
            1, 1, 1
            // @formatter:on
    };
    private static final float FACTOR = 1F;

    public PrewittHorizontalEdgeDetectionFilter() {
        super(PREWITT_OPERATOR, FACTOR);
    }
}
