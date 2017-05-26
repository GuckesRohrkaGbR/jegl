package de.torqdev.jegl.filters.matrix;

import de.torqdev.jegl.filters.ImageFilter;
import org.kohsuke.MetaInfServices;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
@MetaInfServices(value = ImageFilter.class)
public class ReliefFilter extends AbstractMatrixFilter {
    private static final float[] MATRIX = new float[] {
        // @formatter:off
        -2, -1, 0,
        -1, 1, 1,
        0, 1, 2
        // @formatter:on
    };

    private static final float FACTOR = 1F;

    public ReliefFilter() {
        super(MATRIX, FACTOR);
    }
}
