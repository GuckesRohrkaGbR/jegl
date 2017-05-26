package de.torqdev.jegl.filters.matrix;

import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.filters.grayscale.AverageGrayscaleFilter;
import org.kohsuke.MetaInfServices;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by jonas on 26.05.17.
 */
@MetaInfServices
public class PrewittHorizontalEdgeDetectionFilter extends AbstractEdgeDetectorFilter {
    private static final float[] prewittOperator = new float[] {
            -1, -1, -1,
            0, 0, 0,
            1, 1, 1
    };
    private static final float factor = 1F;

    public PrewittHorizontalEdgeDetectionFilter() {
        super(prewittOperator, factor);
    }
}
