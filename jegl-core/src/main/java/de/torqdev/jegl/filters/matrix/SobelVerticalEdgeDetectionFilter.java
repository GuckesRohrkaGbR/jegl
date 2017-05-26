package de.torqdev.jegl.filters.matrix;

import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.filters.grayscale.AverageGrayscaleFilter;
import org.kohsuke.MetaInfServices;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
@MetaInfServices
public class SobelVerticalEdgeDetectionFilter extends AbstractEdgeDetectorFilter {
    private static final float[] sobelOperator = new float[]{
            // @formatter:off
            1, 0, -1,
            2, 0, -2,
            1, 0, -1,
            // @formatter:on
    };
    private static final float factor = 1F;

    public SobelVerticalEdgeDetectionFilter() {
        super(sobelOperator, factor);
    }
}
