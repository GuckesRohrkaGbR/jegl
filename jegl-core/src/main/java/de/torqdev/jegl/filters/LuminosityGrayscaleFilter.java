package de.torqdev.jegl.filters;

import de.torqdev.jegl.core.FloatImage;
import org.kohsuke.MetaInfServices;

/**
 * @author <a href="mailto:jonas.egert@torq-dev.de">Jonas Egert</a>
 * @version 1.0
 */
@MetaInfServices
public class LuminosityGrayscaleFilter implements ImageFilter {
    @Override
    public FloatImage processImage(FloatImage image) {
        return new FloatImage(image.getWidth(), image.getHeight(), 1);
    }
}
