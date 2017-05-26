package de.torqdev.jegl.filters.matrix;

import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.filters.ImageFilter;
import de.torqdev.jegl.filters.grayscale.AverageGrayscaleFilter;
import org.kohsuke.MetaInfServices;

/**
 * Created by jonas on 26.05.17.
 */
@MetaInfServices(value = ImageFilter.class)
public class ScharrHorizontalEdgeDetectionFilter extends AbstractEdgeDetectorFilter {


    private static final float[] scharrOperator = new float[]{
            // @formatter:off
            3, 10, 3,
            0, 0, 0,
            -3, -10, -3,
            // @formatter:on
    };
    private static final float factor = 1F;

    public ScharrHorizontalEdgeDetectionFilter() {
        super(scharrOperator, factor);
    }
}


