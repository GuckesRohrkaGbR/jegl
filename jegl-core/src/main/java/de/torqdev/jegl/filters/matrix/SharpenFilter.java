package de.torqdev.jegl.filters.matrix;

import de.torqdev.jegl.filters.ImageFilter;
import org.kohsuke.MetaInfServices;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
@MetaInfServices(value = ImageFilter.class)
public class SharpenFilter extends AbstractMatrixFilter {
    private static final float[] MATRIX = new float[] {
            // @formatter:off
            0, -1, 0,
            -1, 5, -1,
            0, -1, 0
            // @formatter:on
    };

    private static final float FACTOR = 1F;

    public SharpenFilter() {
        super(MATRIX, FACTOR);
    }
}
