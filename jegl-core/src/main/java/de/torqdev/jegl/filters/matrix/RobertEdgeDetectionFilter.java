package de.torqdev.jegl.filters.matrix;

import de.torqdev.jegl.filters.ImageFilter;
import org.kohsuke.MetaInfServices;

/**
 * @author <a href="mailto:jonas.egert@torq-dev.de">Jonas Egert</a>
 * @version 1.0
 */
@MetaInfServices(value = ImageFilter.class)
public class RobertEdgeDetectionFilter extends AbstractEdgeDetectorFilter{
    private static final float[] ROBERT_OPERATOR = new float[]{
            1, 0, 0,
            0, -1, 0,
            0, 0, 0
    };
    private static final float FACTOR = 1f;

    public RobertEdgeDetectionFilter() {
        super(ROBERT_OPERATOR, FACTOR);
    }
}
