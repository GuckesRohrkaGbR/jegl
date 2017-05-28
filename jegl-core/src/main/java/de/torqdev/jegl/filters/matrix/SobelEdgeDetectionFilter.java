package de.torqdev.jegl.filters.matrix;

import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.filters.ImageFilter;
import org.kohsuke.MetaInfServices;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
@MetaInfServices
public class SobelEdgeDetectionFilter implements ImageFilter {
    private static final ImageFilter combinationFilter =
            new GeometricSquareMeanFilter(
                    new SobelVerticalEdgeDetectionFilter(),
                    new SobelHorizontalEdgeDetectionFilter()
            );

    @Override
    public FloatImage processImage(FloatImage image) {
        return combinationFilter.processImage(image);
    }
}
