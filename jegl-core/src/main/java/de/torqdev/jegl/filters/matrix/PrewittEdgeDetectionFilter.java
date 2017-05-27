package de.torqdev.jegl.filters.matrix;

import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.filters.ImageFilter;
import org.kohsuke.MetaInfServices;

/**
 * @author <a href="mailto:jonas.egert@torq-dev.de">Jonas Egert</a>
 * @version 1.0
 */
@MetaInfServices
public class PrewittEdgeDetectionFilter implements ImageFilter {
    private static final ImageFilter combinationFilter =
            new GeometricSquareMeanFilter(
                    new PrewittVerticalEdgeDetectionFilter(),
                    new PrewittHorizontalEdgeDetectionFilter()
            );

    @Override
    public FloatImage processImage(FloatImage image) {
        return combinationFilter.processImage(image);
    }
}