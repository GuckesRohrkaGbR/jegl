package de.torqdev.jegl.filters.matrix;

import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.filters.grayscale.AverageGrayscaleFilter;
import org.kohsuke.MetaInfServices;

/**
 * Created by jonas on 26.05.17.
 */
@MetaInfServices
public class PrewittVerticalEdgeDetectionFilter extends AbstractEdgeDetectorFilter {
    private static final AverageGrayscaleFilter GRAYSCALE_FILTER = new AverageGrayscaleFilter();

    private static final float[] prewittOperator = new float[]{
            -1, 0, 1,
            -1, 0, 1,
            -1, 0, 1,
    };
    private static final float factor = 1F;

    @Override
    public FloatImage processImage(FloatImage image) {
        return super.processImage(GRAYSCALE_FILTER.processImage(image));
    }

    public PrewittVerticalEdgeDetectionFilter() {
        super(prewittOperator, factor);
    }
}


