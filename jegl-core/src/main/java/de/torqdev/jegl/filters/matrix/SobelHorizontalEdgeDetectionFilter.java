package de.torqdev.jegl.filters.matrix;

import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.filters.ImageFilter;
import de.torqdev.jegl.filters.grayscale.AverageGrayscaleFilter;
import org.kohsuke.MetaInfServices;

import java.util.stream.IntStream;

import static java.lang.Math.*;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
@MetaInfServices
public class SobelHorizontalEdgeDetectionFilter extends AbstractEdgeDetectorFilter{
    private static final AverageGrayscaleFilter GRAYSCALE_FILTER = new AverageGrayscaleFilter();

    private static final float[] sobelOperator = new float[]{
            // @formatter:off
            1, 2, 1,
            0, 0, 0,
            -1, -2, -1,
            // @formatter:on
    };
    private static final float factor = 1F;

    @Override
    public FloatImage processImage(FloatImage image) {
        return super.processImage(GRAYSCALE_FILTER.processImage(image));
    }

    public SobelHorizontalEdgeDetectionFilter() {
        super(sobelOperator, factor);
    }
}
